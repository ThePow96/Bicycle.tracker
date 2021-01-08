package com.example.bicycletracker.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.bicycletracker.R
import com.example.bicycletracker.model.PlacesData
import com.example.bicycletracker.model.RoutesData
import kotlinx.android.synthetic.main.place_details_layout.*

private const val PLACES_OBJECT_LABEL = "complexObject"
private const val ITEM_POSITION = "positionItem"
private const val DECISION_LABEL = "decisionLabel"

class PlaceDetailsActivity : AppCompatActivity() {
    private lateinit var link: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.place_details_layout)
        val decisionValue = intent.getIntExtra(DECISION_LABEL, 0)
        val position = intent.getIntExtra(ITEM_POSITION, 0)

        if (decisionValue == 0) {
            link = "https://raw.githubusercontent.com/ThePow96/Bicycle.tracker/master/screens/places/$position.jpg"
            val intent = intent.getSerializableExtra(PLACES_OBJECT_LABEL) as PlacesData
            place_name.text = intent.name
            description.text = intent.description
            place_coordinates.text = intent.longitude.toString() + ", " + intent.latitude
            loadImageFromHttp(link)
        }else{
            link = "https://raw.githubusercontent.com/ThePow96/Bicycle.tracker/master/screens/routesLinks/$position.png"
            val intent = intent.getSerializableExtra(PLACES_OBJECT_LABEL) as RoutesData
            place_name.text = intent.name
            description.text = intent.description
            place_coordinates.text = intent.level
            loadImageFromHttp(link)
        }
    }

    private fun loadImageFromHttp(url: String){
        Glide.with(this).load(url).centerCrop().into(imageView8)
    }
}