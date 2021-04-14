package com.example.projet_android_eseo_s8_2021.data.sharepreference

import android.content.Context
import android.content.SharedPreferences
import android.location.Location
import com.example.projet_android_eseo_s8_2021.data.model.location.LocationItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class LocalPreferences private constructor(context: Context) {

    private val sharedPref: SharedPreferences =
        context.getSharedPreferences("B2S_pref", Context.MODE_PRIVATE)

    companion object {
        private var INSTANCE: LocalPreferences? = null

        fun getInstance(context: Context): LocalPreferences {
            return INSTANCE?.let {
                INSTANCE
            } ?: run {
                INSTANCE = LocalPreferences(context)
                return INSTANCE!!
            }
        }
    }

    fun saveNewLocation(newLocation: Location) {
        //On récupère la liste des Location déjà enregistré
        var listLocationItem: ArrayList<LocationItem> = getAllLocation()

        //On ajoute la nouvelle Location
        listLocationItem.add(LocationItem(newLocation.latitude, newLocation.longitude))

        //On enregistre la nouvelle liste
        var gson = Gson();
        var json = gson.toJson(listLocationItem);
        sharedPref.edit().putString("location_history", json).apply()
    }

    fun getAllLocation(): ArrayList<LocationItem> {
        val serializedObject: String? = sharedPref.getString("location_history", null)
        if (serializedObject != null) {
            val gson = Gson()
            val type: Type = object : TypeToken<ArrayList<LocationItem?>?>() {}.type
            return gson.fromJson<ArrayList<LocationItem>>(serializedObject, type)
        }else {
            return ArrayList<LocationItem>()
        }
    }
}