package com.example.projet_android_eseo_s8_2021.ui.localisation

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.projet_android_eseo_s8_2021.BuildConfig
import com.example.projet_android_eseo_s8_2021.R
import com.example.projet_android_eseo_s8_2021.data.model.location.LocationItem
import com.example.projet_android_eseo_s8_2021.data.sharepreference.LocalPreferences
import com.example.projet_android_eseo_s8_2021.databinding.ActivityLocalisationBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.util.*

class LocalisationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLocalisationBinding
    private val PERMISSION_REQUEST_LOCATION = 9999
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    companion object {
        fun getStartedIntent(context: Context): Intent {
            return Intent(context, LocalisationActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLocalisationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.apply {
            title = getString(R.string.localisation)
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
        //Gestion des click boutons
        binding.btnSearchLocation?.setOnClickListener {
            requestPermission()
        }
    }

    private fun hasPermission(): Boolean {
        return ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission() {
        if (!hasPermission()) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                PERMISSION_REQUEST_LOCATION
            )
        } else {
            getLastLocationNewMethod()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSION_REQUEST_LOCATION -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    // Permission obtenue, Nous continuons la suite de la logique.
                    getLastLocationNewMethod()
                } else {
                    if (!shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
                        MaterialAlertDialogBuilder(this)
                            .setTitle(resources.getString(R.string.dialog_title))
                            .setMessage(resources.getString(R.string.dialog_message_refuser_tout_le_temps))
                            .setPositiveButton(resources.getString(R.string.daccord)) { dialog, which ->
                                startActivity(
                                    Intent(
                                        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                        Uri.parse("package:" + BuildConfig.APPLICATION_ID)
                                    )
                                )
                            }
                            .setNegativeButton(R.string.refuser) { dialog, which ->
                            }
                            .show()

                    } else {
                        MaterialAlertDialogBuilder(this)
                            .setTitle(resources.getString(R.string.dialog_title))
                            .setMessage(resources.getString(R.string.dialog_message))
                            .setPositiveButton(resources.getString(R.string.daccord)) { dialog, which ->
                            }
                            .show()
                    }
                }
                return
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLastLocationNewMethod() {
        if (hasPermission()) {
            fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
            fusedLocationClient.getCurrentLocation(
                LocationRequest.PRIORITY_HIGH_ACCURACY,
                CancellationTokenSource().token
            )
                .addOnSuccessListener { geoCode(it) }
                .addOnFailureListener {
                    Toast.makeText(this, "Localisation impossible", Toast.LENGTH_SHORT).show()
                }
        }
    }

    private fun geoCode(location: Location) {
        val geocoder = Geocoder(this, Locale.getDefault())
        val results = geocoder.getFromLocation(location.latitude, location.longitude, 1)
        if (results.isNotEmpty()) {
            var userAdress = results[0]
            binding.txtViewAdress?.text = userAdress.getAddressLine(0)
            var eseoLocation = Location("ESEO")
            eseoLocation.latitude = 47.4933589
            eseoLocation.longitude = -0.5508446
            binding.txtViewDistance.text = location.distanceTo(eseoLocation).toString() + getText(R.string.meter)

            //On enregistre la location pour être afficher dans l'historique
            LocalPreferences.getInstance(this).saveNewLocation(location)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}