package com.example.projet_android_eseo_s8_2021.ui.settings.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projet_android_eseo_s8_2021.R
import com.example.projet_android_eseo_s8_2021.data.model.recyclerview.ItemSetting

class SettingsListAdapter(private var itemSettingArray: Array<ItemSetting>) :
    RecyclerView.Adapter<SettingsListAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun showItem(item: ItemSetting) {
            itemView.findViewById<TextView>(R.id.settings_title).text = item.title
            itemView.findViewById<ImageView>(R.id.settings_item_icone).setImageResource(item.icone)
            itemView.setOnClickListener{
                item.onClick()
            }
        }
    }

    // Retourne une « vue » / « layout » pour chaque élément de la liste
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_settings_list, parent, false)
        return ViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return itemSettingArray.size
    }

    // Connect la vue ET la données
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.showItem(itemSettingArray[position])
    }
}