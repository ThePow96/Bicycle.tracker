package com.example.bicycletracker.view

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.bicycletracker.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.tasks.Task
import kotlinx.android.synthetic.main.alarm_layout.*

private const val GPS_STATUS = ""
private const val ALARM_NUMBER = "112"
class LastLocationActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.alarm_layout)
        lokalizacja.setOnClickListener {
            askForGPSPermission()
        }
        alarm_number.setOnClickListener {
            makeAlarmDial()
        }
    }

    @SuppressLint("VisibleForTests")
    private fun askForGPSPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION), 400)
            Log.w(GPS_STATUS, "asking for permission")
            Toast.makeText(this, resources.getString(R.string.app_gps_requirement), Toast.LENGTH_SHORT).show()
            val getLastLocation = FusedLocationProviderClient(this).lastLocation
            getLastLocation(getLastLocation)
        }else{
            val getLastLocation = FusedLocationProviderClient(this).lastLocation
            getLastLocation(getLastLocation)
        }
    }

    private fun getLastLocation(lastLocation: Task<Location>) {
            lastLocation.addOnSuccessListener {
                if (it == null){
                    Toast.makeText(this, resources.getString(R.string.gps_loading_failed), Toast.LENGTH_SHORT).show()
                    myLocation.text = resources.getString(R.string.gps_coordinates, 0f, 0f)
                }else{
                    myLocation.text = resources.getString(R.string.gps_coordinates, it.longitude, it.latitude)
                }
                Log.w(GPS_STATUS, "success")
            }.addOnFailureListener {
                Log.w(GPS_STATUS, "failed")
            }
    }

    private fun makeAlarmDial(){
        val callIntent = Intent(Intent.ACTION_DIAL)
        callIntent.data = Uri.parse("tel:$ALARM_NUMBER")
        startActivity(callIntent)
    }
}