package com.example.bicycletracker.view.interfaces

import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.bicycletracker.R
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.android.synthetic.main.special_places_layout.*


class LastLocationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.special_places_layout)
        lokalizacja.setOnClickListener{getLastLocation()}

    }

    private fun getLastLocation() {
        println("zaczyna")
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            println("odmowa")
            return
        }
        val getLastLocationTask = FusedLocationProviderClient(this).lastLocation

        getLastLocationTask.addOnSuccessListener {
            Toast.makeText(this, "Twoja lokalizacja to: $it", Toast.LENGTH_SHORT).show()
            myLocation.text = it.toString()
            println("success: " + it)
        }
        getLastLocationTask.addOnFailureListener {
            Toast.makeText(this, "Nie określono lokalizacji gps", Toast.LENGTH_SHORT).show()
            println("error: " + it)
        }
    }
}

