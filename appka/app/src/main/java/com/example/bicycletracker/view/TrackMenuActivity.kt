package com.example.bicycletracker.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bicycletracker.R
import com.example.bicycletracker.view.adapter.RoutesAdapter
import com.example.bicycletracker.view.interfaces.ActionButton
import kotlinx.android.synthetic.main.track_menu_layout.*

private const val GOOGLE_MAPS_PACKAGE = "com.google.android.apps.maps"
class TrackMenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.track_menu_layout)
        buildRecyclerView()
    }

    private fun buildRecyclerView(){
        val routesLabels = resources.getStringArray(R.array.routesNames)
        val routesLinks = resources.getStringArray(R.array.routesLinks)
        val routesAdapter = RoutesAdapter(this, routesLabels, routesLinks)
        routes_recycler.adapter = routesAdapter
        routesAdapter.setOnItemClickListener(object: ActionButton{
            override fun buttonPressed(text: String) {
                openMapWithRoute(text)
            }
        })
    }

    private fun openMapWithRoute(url: String) {
        intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        intent.setPackage(GOOGLE_MAPS_PACKAGE)
        startActivity(intent)
    }
}
