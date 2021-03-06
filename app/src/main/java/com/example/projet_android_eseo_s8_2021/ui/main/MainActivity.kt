package com.example.projet_android_eseo_s8_2021.ui.main

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.example.projet_android_eseo_s8_2021.R
import com.example.projet_android_eseo_s8_2021.data.sharepreference.LocalPreferences
import com.example.projet_android_eseo_s8_2021.databinding.ActivityMainBinding
import com.example.projet_android_eseo_s8_2021.ui.history.HistoryActivity
import com.example.projet_android_eseo_s8_2021.ui.localisation.LocalisationActivity
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

        //Gestion des clicks bouttons
        binding.mainActivityImvSettings.setOnClickListener {
            startActivity(SettingsActivity.getStartedIntent(this))
        }
        binding.mainActivityBtnLocation.setOnClickListener {
            startActivity(LocalisationActivity.getStartedIntent(this))
        }
        binding.mainActivityBtnHistory.setOnClickListener {
            if (LocalPreferences.getInstance(this).getAllLocation().size == 0)
                Toast.makeText(this, getString(R.string.impossible_no_history), Toast.LENGTH_SHORT).show()
            else
                startActivity(HistoryActivity.getStartedIntent(this))
        }

        startAllAnimation()
    }

    private fun startAllAnimation() {
        //On fait tourner la roue des settings ici
        val rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate_animation)
        binding.mainActivityImvSettings.animation = rotateAnimation

        //On translate le logo ici
        val translationXFromRightAnimation =
            AnimationUtils.loadAnimation(this, R.anim.translation_x_from_right_animation)
        binding.mainActivityAppLogo.animation = translationXFromRightAnimation

        //On translate le bouton 'location' ici
        binding.mainActivityBtnLocation.animation = translationXFromRightAnimation

        //On translate le bouton 'location' ici
        val translationXFromLeftAnimation =
            AnimationUtils.loadAnimation(this, R.anim.translation_x_from_left_animation)
        binding.mainActivityBtnHistory.animation = translationXFromLeftAnimation
    }
}