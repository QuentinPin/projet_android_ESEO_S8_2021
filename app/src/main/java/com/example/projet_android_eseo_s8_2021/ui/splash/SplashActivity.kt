package com.example.projet_android_eseo_s8_2021.ui.splash

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.projet_android_eseo_s8_2021.R
import com.example.projet_android_eseo_s8_2021.ui.main.MainActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(MainActivity.getStartedIntent(this))
            finish()
        }, 2000)
    }
}