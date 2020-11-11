package com.example.bicycletracker.view

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bicycletracker.R
import kotlinx.android.synthetic.main.settings_layout.*
import java.util.*

private const val PREFERENCES_LANGUAGE_LABEL = "Language"
class SettingsActivity : AppCompatActivity(){
    private var defaultLanguage = ""
    private var defaultBackground = ""

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_layout)
        loadAppLanguage()
        settings_save_button.setOnClickListener {
            Toast.makeText(this, "App language changed to: " + defaultLanguage, Toast.LENGTH_SHORT).show()
            setAppDefaultLanguage(defaultLanguage)
            recreate()
        }
        showChosenLanguage()
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
        defaultLanguage = preferences.getString(PREFERENCES_LANGUAGE_LABEL, "pl")!!
        setAppDefaultLanguage(defaultLanguage)
    }

    fun changeAppLanguage(v: View) {
        when(v.id){
            R.id.language_english ->{
                defaultLanguage = "en"
            }
            R.id.language_polish ->{
                defaultLanguage = "pl"
            }
            R.id.language_germany ->{
                defaultLanguage = "de"
            }
        }
        showChosenLanguage()
    }

    private fun showChosenLanguage(){
        when (defaultLanguage){
            "pl" ->{
                setNotChosenAlpha()
                language_polish.alpha = 1f
            }
            "de" ->{
                setNotChosenAlpha()
                language_germany.alpha = 1f
            }
            "en" ->{
                setNotChosenAlpha()
                language_english.alpha = 1f
            }
            else -> {
                setNotChosenAlpha()
                language_polish.alpha = 1f
            }
        }
    }

    private fun setNotChosenAlpha(){
        language_english.alpha = 0.3f
        language_germany.alpha = 0.3f
        language_polish.alpha = 0.3f
    }
}