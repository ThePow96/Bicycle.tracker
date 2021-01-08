package com.example.bicycletracker.view.adapter

import android.content.Context
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bicycletracker.R
import com.example.bicycletracker.model.PlacesData
import com.example.bicycletracker.view.interfaces.ActionButton
import com.example.bicycletracker.view.interfaces.TransferIntentDetails
import kotlinx.android.synthetic.main.create_journey_row.view.*
import kotlinx.android.synthetic.main.single_element_border.view.*


class SpecialPlacesAdapter(private val context: Context, private val placesData: List<PlacesData>) : RecyclerView.Adapter<SpecialPlacesAdapter.GamesViewHolder>() {

    private lateinit var buttonClickListener: TransferIntentDetails

    fun setOnItemClickListener(itemClickListener: TransferIntentDetails){
        this.buttonClickListener = itemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GamesViewHolder {
        return GamesViewHolder(LayoutInflater.from(context).inflate(R.layout.single_element_border, parent, false))
    }

    class GamesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val element: TextView = itemView.single_label
        val button: Button = itemView.button
    }

    override fun getItemCount(): Int {
        return placesData.size
    }

    override fun onBindViewHolder(holder: GamesViewHolder, position: Int) {
        val objIncome = placesData[position]
        holder.element.text = objIncome.name

        holder.button.setOnClickListener {
            buttonClickListener.moveDetails(placesData[position], position)
        }
    }
}