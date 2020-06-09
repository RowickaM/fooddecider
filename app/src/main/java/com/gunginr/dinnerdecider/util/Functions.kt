package com.gunginr.dinnerdecider.util

import android.content.Context
import android.text.Editable
import java.util.*
import kotlin.collections.ArrayList

fun isExist(name: String, list: ArrayList<String>): Boolean {
    return list.find { it.toUpperCase(Locale.getDefault()) == name.toUpperCase(Locale.getDefault()) } != null
}
fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)

fun getResourceId(context: Context, nameOfRes: String): Int {
    val id =  context.resources.getIdentifier(nameOfRes, "drawable", context.packageName )
    return id;
}