package com.gunginr.dinnerdecider.util

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

val gson = Gson()
fun writeToSharedPref(context: Context, list: ArrayList<String>, editor: SharedPreferences.Editor){
    val json = gson.toJson(list)

    editor.putString(LIST_DINNER_KEY, json)
    if(editor.commit()){
        Toast.makeText(context, "dodano do listy", Toast.LENGTH_SHORT).show()
    }
}

fun readFromSharedPref(context: Context, sharedPref: SharedPreferences): ArrayList<String>?{
    val json = sharedPref.getString(LIST_DINNER_KEY, "")
    if(json==""){
        Toast.makeText(context, "brak zapisanych potraw/restauracji", Toast.LENGTH_SHORT).show()
    }else{
        val type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(json, type)
    }
    return null
}