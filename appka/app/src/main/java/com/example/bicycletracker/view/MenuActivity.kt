package com.example.bicycletracker.view

import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.bicycletracker.R
import com.example.bicycletracker.tools.loadAppBackground
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.tasks.Task
import kotlinx.android.synthetic.main.menu_layout.*
import java.util.jar.Manifest

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
            R.id.settings -> {
                intent = Intent(this, SettingsActivity::class.java)
            }
        }
       finish()
        startActivity(intent)
    }

}


