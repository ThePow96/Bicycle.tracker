package com.example.bicycletracker.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.bicycletracker.model.PlacesData
import com.example.bicycletracker.R
import com.example.bicycletracker.repositories.NetworkLoading
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

private const val ZOOM_LVL = 10f
var arrayList = listOf<PlacesData>()
lateinit var mapFragment: SupportMapFragment

class FindUsActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap
    private lateinit var compositeDisposable: CompositeDisposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.find_us_layout)
        mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        compositeDisposable = CompositeDisposable()

        val disposable = Observable.fromCallable { NetworkLoading().loadHttpData() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                arrayList = it
            }
            .doOnComplete {
                mapFragment.getMapAsync(this)
            }
            .subscribe()
        compositeDisposable.add(disposable)
    }

    override fun onMapReady(p0: GoogleMap) {
        mMap = p0
        for (i in arrayList.indices) {
            val position = LatLng(arrayList[i].longitude, arrayList[i].latitude)
            mMap.addMarker(MarkerOptions().position(position).title(arrayList[i].name))
            mMap.moveCamera(CameraUpdateFactory.newLatLng(position))
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position, ZOOM_LVL))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}



