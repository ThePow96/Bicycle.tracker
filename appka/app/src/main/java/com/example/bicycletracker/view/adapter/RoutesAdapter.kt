package com.example.bicycletracker.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bicycletracker.R
import com.example.bicycletracker.model.RoutesData
import com.example.bicycletracker.view.interfaces.TransferRoutesDetails
import kotlinx.android.synthetic.main.single_element_border.view.*


class RoutesAdapter(private val context: Context, private val placesData: List<RoutesData>) : RecyclerView.Adapter<RoutesAdapter.GamesViewHolder>() {

    private lateinit var buttonClickListener: TransferRoutesDetails

    fun setOnItemClickListener(itemClickListener: TransferRoutesDetails){
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
            buttonClickListener.moveRoutesDetails(placesData[position], position)
        }
    }
}