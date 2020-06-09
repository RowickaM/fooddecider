package com.gunginr.dinnerdecider.util.storagedata

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.gunginr.dinnerdecider.R
import com.gunginr.dinnerdecider.util.snackbars.createInfoSnackBar
import com.gunginr.dinnerdecider.util.variables.LIST_DINNER_KEY
import com.gunginr.dinnerdecider.util.variables.SHARED_PREF_KEY_DINNER

val gson = Gson()
fun writeToSharedPref(context: Context, list: ArrayList<String>){
    val editor = context.getSharedPreferences(SHARED_PREF_KEY_DINNER, AppCompatActivity.MODE_PRIVATE).edit()
    val json = gson.toJson(list)

    editor.putString(LIST_DINNER_KEY, json).commit()
}

fun readFromSharedPref(context: Context): ArrayList<String>{
    val sharedPref = context.getSharedPreferences(SHARED_PREF_KEY_DINNER, AppCompatActivity.MODE_PRIVATE)
    val json = sharedPref.getString(LIST_DINNER_KEY, "")
    if(json==""){
        createInfoSnackBar(
            context as Activity,
            context.getString(R.string.empty_list)
        ).show()
    }else{
        val type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(json, type)
    }
    return arrayListOf()
}