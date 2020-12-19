package com.example.bicycletracker.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bicycletracker.R
import com.example.bicycletracker.model.PlacesData
import com.example.bicycletracker.repositories.PlacesLoading
import com.example.bicycletracker.view.adapter.JourneyAdapter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.create_journey_layout.*

class CreateJourneyActivity : AppCompatActivity() {
    private lateinit var compositeDisposable: CompositeDisposable
    private lateinit var adapter: JourneyAdapter
    private var placesList = arrayListOf<PlacesData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_journey_layout)
        compositeDisposable = CompositeDisposable()

        val disposable = Observable.fromCallable { PlacesLoading().loadHttpData() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                placesList = it as ArrayList<PlacesData>
            }
            .doOnComplete {
                buildContactList(placesList)
            }
            .subscribe()
        compositeDisposable.add(disposable)

        create.setOnClickListener {
            val coordinatesArray = adapter.getGames()
            showWayPoints(coordinatesArray)
        }
    }

    private fun buildContactList(arrayList: ArrayList<PlacesData>){
        arrayList.removeAt(arrayList.lastIndex)
        adapter = JourneyAdapter(this, arrayList)
        journey_recycler.adapter = adapter
    }

    private fun showWayPoints(cordsArray: ArrayList<String>){
        val origin = "&origin=${cordsArray[0]}"
        val destination = "&destination=${cordsArray[cordsArray.lastIndex]}"
        val travelmode = "&travelmode=bicycling"
        var waypoints = "&waypoints="
        for (i in 1 until cordsArray.size-1){
            waypoints+=cordsArray[i]
            if (i != cordsArray.size-2){
                waypoints+="%7C"
            }
        }
        val url = "https://www.google.com/maps/dir/?api=1$origin$destination$waypoints$travelmode"
        intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK;
        intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
        startActivity(intent)
    }
}
