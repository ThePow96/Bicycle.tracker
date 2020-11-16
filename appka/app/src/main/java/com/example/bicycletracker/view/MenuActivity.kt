package com.example.bicycletracker.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.bicycletracker.R
import com.example.bicycletracker.tools.loadAppBackground
import kotlinx.android.synthetic.main.menu_layout.*

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_layout)
        loadAppBackground(this, menu_layout)
    }

    fun changeMenuOption(v: View) {
        var intent = Intent()
        when (v.id) {
            R.id.Kontakt -> {
                intent = Intent(this, ContactActivity::class.java)
            }
            R.id.Trasy -> {
                intent = Intent(this, TrackMenuActivity::class.java)
            }
            R.id.Znajdz -> {
                intent = Intent(this, FindUsActivity::class.java)
            }
            R.id.special_places -> {
                intent = Intent(this, SpecialPlacesActivity::class.java)
            }
            R.id.settings ->{
                intent = Intent(this, SettingsActivity::class.java)
            }
        }
        finish()
        startActivity(intent)
    }
}