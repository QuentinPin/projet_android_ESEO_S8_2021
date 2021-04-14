package com.example.projet_android_eseo_s8_2021.ui.history

import android.content.Context
import android.content.Intent
import android.location.Geocoder
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projet_android_eseo_s8_2021.R
import com.example.projet_android_eseo_s8_2021.data.model.recyclerview.ItemHistoryLocation
import com.example.projet_android_eseo_s8_2021.data.model.recyclerview.ItemSetting
import com.example.projet_android_eseo_s8_2021.data.sharepreference.LocalPreferences
import com.example.projet_android_eseo_s8_2021.databinding.ActivityHistoryBinding
import com.example.projet_android_eseo_s8_2021.ui.history.adapter.HistoryListAdapter
import com.example.projet_android_eseo_s8_2021.ui.settings.adapter.SettingsListAdapter
import java.util.*

class HistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHistoryBinding

    companion object {
        fun getStartedIntent(context: Context): Intent {
            return Intent(context, HistoryActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.apply {
            title = getString(R.string.history_of_positions)
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        var rvSettings = binding.historyRecyclerview
        rvSettings.layoutManager = LinearLayoutManager(this)
        var arrayOfLocationItem = arrayListOf<ItemHistoryLocation>()
        LocalPreferences.getInstance(this).getAllLocation().forEach {
            val geocoder = Geocoder(this, Locale.getDefault())
            val results = geocoder.getFromLocation(it.latitude, it.longitude, 1)
            if (results.isNotEmpty()) {
                var eseoLocation = Location("ESEO")
                eseoLocation.latitude = 47.4933589
                eseoLocation.longitude = -0.5508446
                var historyLocation = Location("historyLocation")
                historyLocation.latitude = it.latitude
                historyLocation.longitude = it.longitude
                arrayOfLocationItem.add(ItemHistoryLocation(results[0].getAddressLine(0),
                    historyLocation.distanceTo(eseoLocation).toString() + getText(R.string.meter) + " " + getText(R.string.of_eseo)) {
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("geo:" + it.latitude + "," + it.longitude)))
                })
            }
        }
        rvSettings.adapter = HistoryListAdapter(arrayOfLocationItem)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}