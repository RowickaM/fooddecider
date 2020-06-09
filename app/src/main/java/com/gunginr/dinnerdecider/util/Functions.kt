package com.gunginr.dinnerdecider.util

import android.content.Context
import android.text.Editable
import android.view.View
import android.view.inputmethod.InputMethodManager
import java.util.*

fun isExist(name: String, list: ArrayList<String>): Boolean {
    return list.find { it.toUpperCase(Locale.getDefault()) == name.toUpperCase(Locale.getDefault()) } != null
}
fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)

fun getResourceId(context: Context, nameOfRes: String): Int {
    return context.resources.getIdentifier(nameOfRes, "drawable", context.packageName )
}

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}