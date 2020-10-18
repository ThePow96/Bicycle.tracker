package com.example.bicycletracker.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bicycletracker.R
import com.example.bicycletracker.view.interfaces.ActionButton
import kotlinx.android.synthetic.main.single_element_border.view.*

class RoutesAdapter(private val context: Context, private val routesLabels: Array<String?>, private val routesLinks: Array<String?>) : RecyclerView.Adapter<RoutesAdapter.GamesViewHolder>() {
    private lateinit var buttonClickListener: ActionButton

    fun setOnItemClickListener(itemClickListener: ActionButton){
        this.buttonClickListener = itemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GamesViewHolder {
        return GamesViewHolder(LayoutInflater.from(context).inflate(R.layout.single_element_border, parent, false))
    }

    class GamesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val element: TextView = itemView.single_label
        val btn: Button = itemView.button
    }

    override fun getItemCount(): Int {
        return routesLabels.size
    }

    override fun onBindViewHolder(holder: GamesViewHolder, position: Int) {
        val currentLabel = routesLabels[position]
        holder.element.text = currentLabel
        holder.btn.setOnClickListener {
            buttonClickListener.buttonPressed(routesLinks[position]!!)
        }
    }
}