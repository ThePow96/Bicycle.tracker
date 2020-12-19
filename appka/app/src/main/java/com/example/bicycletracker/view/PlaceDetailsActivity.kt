package com.example.bicycletracker.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bicycletracker.R
import com.example.bicycletracker.model.PlacesData
import kotlinx.android.synthetic.main.place_details_layout.*

private const val PLACES_OBJECT_LABEL = "complexObject"
class PlaceDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.place_details_layout)

        val intent = intent.getSerializableExtra(PLACES_OBJECT_LABEL) as PlacesData
        setDataToWidgets(intent)
    }

    private fun setDataToWidgets(item: PlacesData){
        place_name.text = item.name
        place_coordinates.text = item.longitude.toString() + ", " + item.latitude
        description.text = item.description
    }
}

