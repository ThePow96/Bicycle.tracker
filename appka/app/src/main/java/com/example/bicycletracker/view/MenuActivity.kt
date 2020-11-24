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
                //intent = Intent(this, SpecialPlacesActivity::class.java)
                getLastLocation()
            }
            R.id.settings -> {
                /*
                val url =
                   "https://maps.googleapis.com/maps/api/directions/json?" + "origin=Boston,MA&destination=Concord,MA" + "&waypoints=Charlestown,MA|Lexington,MA" + "&key=" + resources.getString(
                        R.string.google_maps_key
                    )
                intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                intent.setPackage(GOOGLE_MAPS_PACKAGE)
                startActivity(intent)
                */

                intent = Intent(this, SettingsActivity::class.java)
            }
        }
       // finish()
       // startActivity(intent)
    }

    private fun getLastLocation(){
        println("zaczyna")
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            println("odmowa")
            return
        }
        val getLastLocationTask = FusedLocationProviderClient(this).lastLocation

        getLastLocationTask.addOnSuccessListener {
            Toast.makeText(this, "Twoja lokalizacja to: $it", Toast.LENGTH_SHORT).show()
            println("success: " + it)
        }
        getLastLocationTask.addOnFailureListener{
            Toast.makeText(this, "Nie określono lokalizacji gps", Toast.LENGTH_SHORT).show()
            println("error: " + it)
        }
    }
}