package com.example.bicycletracker.view.adapter

import android.content.Context
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bicycletracker.R
import com.example.bicycletracker.model.PlacesData
import com.example.bicycletracker.view.interfaces.ActionButton
import kotlinx.android.synthetic.main.create_journey_row.view.*


class JourneyAdapter(private val context: Context, private val placesData: List<PlacesData>) : RecyclerView.Adapter<JourneyAdapter.GamesViewHolder>() {

    private lateinit var buttonClickListener: ActionButton

    fun setOnItemClickListener(itemClickListener: ActionButton){
        this.buttonClickListener = itemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GamesViewHolder {
        return GamesViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.create_journey_row,
                parent,
                false
            )
        )
    }

    class GamesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val element: TextView = itemView.journey_coordinates
        val checkbox: CheckBox = itemView.checkbox_name
    }

    override fun getItemCount(): Int {
        return placesData.size
    }

    override fun onBindViewHolder(holder: GamesViewHolder, position: Int) {
        val objIncome = placesData[position]
        holder.checkbox.text = placesData[position].name
        holder.element.text = placesData[position].longitude.toString() + "," + placesData[position].latitude
        holder.checkbox.setOnCheckedChangeListener(null)

        //in some cases, it will prevent unwanted situations
        holder.checkbox.setOnCheckedChangeListener(null)

        //if true, your checkbox will be selected, else unselected
        holder.checkbox.isChecked = objIncome.checkStatus

        holder.checkbox.setOnCheckedChangeListener { buttonView, isChecked -> //set your object's last status
            objIncome.checkStatus = isChecked
        }
    }

    fun getGames(): ArrayList<String>{
        val coordinatesArray = arrayListOf<String>()
        for (i in placesData.indices){
            if (placesData[i].checkStatus){
                coordinatesArray.add(placesData[i].longitude.toString() + "," + placesData[i].latitude)
            }
        }
        return coordinatesArray
    }
}