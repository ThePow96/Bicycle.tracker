package com.example.bicycletracker.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bicycletracker.R
import com.example.bicycletracker.model.PlacesData
import com.example.bicycletracker.model.RoutesData
import com.example.bicycletracker.repositories.PlacesLoading
import com.example.bicycletracker.repositories.RoutesLoading
import com.example.bicycletracker.view.adapter.RoutesAdapter
import com.example.bicycletracker.view.adapter.SpecialPlacesAdapter
import com.example.bicycletracker.view.interfaces.ActionButton
import com.example.bicycletracker.view.interfaces.TransferIntentDetails
import com.example.bicycletracker.view.interfaces.TransferRoutesDetails
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.special_places_layout.*
import kotlinx.android.synthetic.main.track_menu_layout.*


private const val PLACES_OBJECT_LABEL = "complexObject"
private const val ITEM_POSITION = "positionItem"
private const val DECISION_VALUE = 1
private const val DECISION_LABEL = "decisionLabel"

class TrackMenuActivity : AppCompatActivity() {
    private lateinit var compositeDisposable: CompositeDisposable
    private lateinit var adapter: RoutesAdapter
    private var placesList = arrayListOf<RoutesData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.track_menu_layout)

        compositeDisposable = CompositeDisposable()

        val disposable = Observable.fromCallable { RoutesLoading().loadHttpData() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                placesList = it as ArrayList<RoutesData>
            }
            .doOnComplete {
                buildRecyclerView(placesList)
            }
            .subscribe()
        compositeDisposable.add(disposable)
    }

    private fun buildRecyclerView(arrayList: ArrayList<RoutesData>){
        arrayList.removeAt(arrayList.lastIndex)
        adapter = RoutesAdapter(this, arrayList)
        routes_recycler.adapter = adapter

        adapter.setOnItemClickListener(object : TransferRoutesDetails {

            override fun moveRoutesDetails(item: RoutesData, position: Int) {
                val dataIntent = Intent(applicationContext, PlaceDetailsActivity::class.java)
                dataIntent.putExtra(PLACES_OBJECT_LABEL, item)
                dataIntent.putExtra(ITEM_POSITION, position)
                dataIntent.putExtra(DECISION_LABEL, DECISION_VALUE)
                startActivity(dataIntent)
            }
        })
    }
}
