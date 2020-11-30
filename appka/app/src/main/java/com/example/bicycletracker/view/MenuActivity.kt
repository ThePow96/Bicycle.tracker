package com.example.bicycletracker.view

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.example.bicycletracker.R
import com.example.bicycletracker.tools.loadAppBackground
import kotlinx.android.synthetic.main.alert_leave.view.*
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
                //intent = Intent(this, SpecialPlacesActivity::class.java)
                showWaypoints()
            }
            R.id.settings -> {
                intent = Intent(this, SettingsActivity::class.java)
            }
            R.id.alarm_data -> {
                intent = Intent(this, LastLocationActivity::class.java)
            }
        }
       // startActivity(intent)
    }

    private fun showWaypoints(){
        val url = "https://www.google.com/maps/dir/?api=1&origin=54.1194055,15.2053655&destination=54.0954555,15.0828297&waypoints=54.0942191,15.1077139%7C54.0966391,15.0977442&travelmode=bicycling"
        intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK;
        intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
        startActivity(intent)
    }

    override fun onBackPressed() {
        showConfirmationAlert()
    }

    private fun showConfirmationAlert() {
        val bindingLeave = LayoutInflater.from(this).inflate(R.layout.alert_leave, null)
        val alert = Dialog(this)
        alert.requestWindowFeature(Window.FEATURE_NO_TITLE)
        alert.setCanceledOnTouchOutside(true)
        alert.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        alert.setContentView(bindingLeave.rootView)
        bindingLeave.confirm.setOnClickListener {
            alert.dismiss()
            finish()
        }
        bindingLeave.cancel.setOnClickListener {
            alert.dismiss()

        }
        alert.setOnCancelListener {
            alert.dismiss()
        }
        alert.show()
    }
}


