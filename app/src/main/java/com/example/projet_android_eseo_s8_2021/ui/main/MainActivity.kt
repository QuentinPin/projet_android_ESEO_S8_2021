package com.example.projet_android_eseo_s8_2021.ui.main

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import com.example.projet_android_eseo_s8_2021.R
import com.example.projet_android_eseo_s8_2021.databinding.ActivityMainBinding
import com.example.projet_android_eseo_s8_2021.ui.settings.SettingsActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    companion object {
        fun getStartedIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.mainActivityImvSettings.setOnClickListener {
            startActivity(SettingsActivity.getStartedIntent(this))
        }
    }
}