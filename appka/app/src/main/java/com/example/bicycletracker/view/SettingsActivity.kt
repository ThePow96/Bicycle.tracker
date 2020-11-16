package com.example.bicycletracker.view

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.bicycletracker.R
import com.example.bicycletracker.tools.*
import kotlinx.android.synthetic.main.settings_layout.*
import java.util.*

private const val PREFERENCES_LANGUAGE_LABEL = "Language"
private const val GERMANY_LANGUAGE = "de"
private const val POLISH_LANGUAGE = "pl"
private const val ENGLISH_LANGUAGE = "en"
const val NORMAL_VISIBILITY = 1f
const val REDUCED_VISIBILITY = .2f

class SettingsActivity : AppCompatActivity(){
    private var defaultLanguage = ""
    private var defaultBackground  = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_layout)
        loadAppLanguage()
        showChosenLanguage()
        loadAppBackground(this, settings_mainLayout)
        showChosenBackground(background_dark_gray, background_light_gray, background_pink_gradient)
        settings_save_button.setOnClickListener {
            setAppDefaultLanguage(defaultLanguage)
            setAppDefaultBackground(this, defaultBackground)
            recreate()
        }
    }

    private fun setAppDefaultLanguage(lng: String) {
        val locale = Locale(lng)
        Locale.setDefault(locale)
        val cfg = Configuration()
        cfg.locale = locale
        baseContext.resources.updateConfiguration(cfg, baseContext.resources.displayMetrics)
        val editor = getSharedPreferences(PREFERENCES_LANGUAGE_LABEL, MODE_PRIVATE).edit()
        editor.putString(PREFERENCES_LANGUAGE_LABEL, lng)
        editor.apply()
    }

    private fun loadAppLanguage() {
        val preferences = getSharedPreferences(PREFERENCES_LANGUAGE_LABEL, MODE_PRIVATE)
        defaultLanguage = preferences.getString(PREFERENCES_LANGUAGE_LABEL, POLISH_LANGUAGE)!!
        setAppDefaultLanguage(defaultLanguage)
    }

    fun changeAppLanguage(v: View) {
        when(v.id){
            R.id.language_english ->{
                defaultLanguage = ENGLISH_LANGUAGE
            }
            R.id.language_polish ->{
                defaultLanguage = POLISH_LANGUAGE
            }
            R.id.language_germany ->{
                defaultLanguage = GERMANY_LANGUAGE
            }
        }
        showChosenLanguage()
    }

    private fun showChosenLanguage(){
        when (defaultLanguage){
            GERMANY_LANGUAGE ->{
                setNotChosenLanguageAlpha()
                language_germany.alpha = NORMAL_VISIBILITY
            }
            ENGLISH_LANGUAGE ->{
                setNotChosenLanguageAlpha()
                language_english.alpha = NORMAL_VISIBILITY
            }
            else -> {
                setNotChosenLanguageAlpha()
                language_polish.alpha = NORMAL_VISIBILITY
            }
        }
    }

    private fun setNotChosenLanguageAlpha(){
        language_english.alpha = REDUCED_VISIBILITY
        language_germany.alpha = REDUCED_VISIBILITY
        language_polish.alpha = REDUCED_VISIBILITY
    }

    fun changeDefaultBackground(v: View){
       defaultBackground = when (v.id){
            R.id.background_dark_gray -> {
                R.color.dark_gray
            }
            R.id.background_light_gray -> {
                R.color.litleGrey
            }
            else -> {
                R.drawable.background_gradient
            }
        }
        showChosenBackgroundButton(background_dark_gray, background_light_gray, background_pink_gradient, defaultBackground)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
        startActivity(Intent(this, MenuActivity::class.java))
    }
}