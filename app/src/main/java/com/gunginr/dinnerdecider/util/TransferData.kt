package com.gunginr.dinnerdecider.util

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

val gson = Gson()
fun writeToSharedPref(context: Context, list: ArrayList<String>){
    val editor = context.getSharedPreferences(SHARED_PREF_KEY, AppCompatActivity.MODE_PRIVATE).edit()
    val json = gson.toJson(list)

    editor.putString(LIST_DINNER_KEY, json).commit()
}

fun readFromSharedPref(context: Context): ArrayList<String>{
    val sharedPref = context.getSharedPreferences(SHARED_PREF_KEY, AppCompatActivity.MODE_PRIVATE)
    val json = sharedPref.getString(LIST_DINNER_KEY, "")
    if(json==""){
        Toast.makeText(context, "brak zapisanych potraw/restauracji", Toast.LENGTH_SHORT).show()
    }else{
        val type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(json, type)
    }
    return arrayListOf()
}