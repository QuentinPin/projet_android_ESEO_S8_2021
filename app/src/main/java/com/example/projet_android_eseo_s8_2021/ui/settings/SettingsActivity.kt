package com.example.projet_android_eseo_s8_2021.ui.settings

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projet_android_eseo_s8_2021.R
import com.example.projet_android_eseo_s8_2021.data.model.recyclerview.ItemSetting
import com.example.projet_android_eseo_s8_2021.databinding.ActivitySettingsBinding
import com.example.projet_android_eseo_s8_2021.ui.settings.adapter.SettingsListAdapter

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding

    companion object {
        fun getStartedIntent(context: Context): Intent {
            return Intent(context, SettingsActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.apply {
            setTitle(R.string.settings)
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        var rvSettings = binding.historyRecyclerview
        rvSettings.layoutManager = LinearLayoutManager(this)
        var arrayOfSetting = arrayOf(
            ItemSetting(
                getString(R.string.settings_of_the_app), R.drawable.ic_baseline_settings_24
            ) {
                val targetIntent = Intent().apply {
                    action = android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                }
                val uri = Uri.fromParts("package", packageName, null)
                targetIntent.data = uri
                startActivity(targetIntent)
            },
            ItemSetting(
                getString(R.string.settings_of_location), R.drawable.ic_baseline_edit_location_alt_24
            ) {
                val targetIntent = Intent().apply {
                    action = android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS
                }
                startActivity(targetIntent)
            },
            ItemSetting(
                getString(R.string.where_is_eseo), R.drawable.ic_baseline_location_on_24
            ) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("geo:47.4933589,-0.5508446")))
            },
            ItemSetting(
                getString(R.string.eseo_website), R.drawable.ic_baseline_web_asset_24
            ) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://eseo.fr/")))
            },
            ItemSetting(
                getString(R.string.contacte_me), R.drawable.ic_baseline_email_24
            ) {
                startActivity(Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "quentin_pineau@reseau.eseo.fr", null)))
            })

        rvSettings.adapter = SettingsListAdapter(arrayOfSetting)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}