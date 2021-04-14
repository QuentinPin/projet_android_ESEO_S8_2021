package com.example.projet_android_eseo_s8_2021.ui.history.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projet_android_eseo_s8_2021.R
import com.example.projet_android_eseo_s8_2021.data.model.recyclerview.ItemHistoryLocation

class HistoryListAdapter (private var itemLocationArray: ArrayList<ItemHistoryLocation>) :
    RecyclerView.Adapter<HistoryListAdapter.ViewHolder>()  {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun showItem(item: ItemHistoryLocation) {
            itemView.findViewById<TextView>(R.id.history_location_addresse).text = item.address
            itemView.findViewById<TextView>(R.id.history_location_distance).text = item.distance
            itemView.setOnClickListener{
                item.onClick()
            }
        }
    }

    // Retourne une « vue » / « layout » pour chaque élément de la liste
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_history_list, parent, false)
        return ViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return itemLocationArray.size
    }

    // Connect la vue ET la données
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.showItem(itemLocationArray[position])
    }
}