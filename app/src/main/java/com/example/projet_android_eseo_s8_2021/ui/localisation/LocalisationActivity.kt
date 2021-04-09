package com.example.projet_android_eseo_s8_2021.ui.localisation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.projet_android_eseo_s8_2021.R
import com.example.projet_android_eseo_s8_2021.databinding.ActivityLocalisationBinding
import com.example.projet_android_eseo_s8_2021.databinding.ActivityMainBinding
import com.example.projet_android_eseo_s8_2021.databinding.ActivitySettingsBinding
import com.example.projet_android_eseo_s8_2021.ui.main.MainActivity

class LocalisationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLocalisationBinding

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
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}