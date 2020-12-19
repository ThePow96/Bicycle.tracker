package com.example.bicycletracker.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bicycletracker.R
import com.example.bicycletracker.model.PlacesData
import com.example.bicycletracker.repositories.PlacesLoading
import com.example.bicycletracker.view.adapter.SpecialPlacesAdapter
import com.example.bicycletracker.view.interfaces.TransferIntentDetails
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.special_places_layout.*

private const val PLACES_OBJECT_LABEL = "complexObject"
class SpecialPlacesActivity : AppCompatActivity() {
    private lateinit var compositeDisposable: CompositeDisposable
    private lateinit var adapter: SpecialPlacesAdapter
    private var placesList = arrayListOf<PlacesData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.special_places_layout)

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
    }

    private fun buildContactList(arrayList: ArrayList<PlacesData>){
        arrayList.removeAt(arrayList.lastIndex)
        adapter = SpecialPlacesAdapter(this, arrayList)
        special_places_recycler.adapter = adapter

        adapter.setOnItemClickListener(object : TransferIntentDetails {
            override fun moveDetails(item: PlacesData) {
                val dataIntent = Intent(applicationContext, PlaceDetailsActivity::class.java)
                dataIntent.putExtra(PLACES_OBJECT_LABEL, item)
                startActivity(dataIntent)
            }
        })
    }
}

