package com.example.bicycletracker.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bicycletracker.model.PlacesData
import com.example.bicycletracker.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

private const val ZOOM_LVL = 10f
private const val PLACES_OBJECT_LABEL = "complexObject"
private lateinit var mapFragmentPlaces: SupportMapFragment
private lateinit var currentPlace: PlacesData

class MapActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.find_us_layout)
        currentPlace = intent.getSerializableExtra(PLACES_OBJECT_LABEL) as PlacesData
        mapFragmentPlaces = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragmentPlaces.getMapAsync(this)
    }

    override fun onMapReady(p0: GoogleMap) {
        mMap = p0
        mMap.clear()
        val position = LatLng(currentPlace.longitude, currentPlace.latitude)
        mMap.addMarker(MarkerOptions().position(position).title(currentPlace.name))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(position))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position, ZOOM_LVL))
    }
}



