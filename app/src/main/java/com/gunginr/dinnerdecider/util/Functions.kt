package com.gunginr.dinnerdecider.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.text.Editable
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.google.android.gms.common.util.Base64Utils
import java.util.*

fun isExist(name: String, list: ArrayList<String>): Boolean {
    return list.find { it.toUpperCase(Locale.getDefault()) == name.toUpperCase(Locale.getDefault()) } != null
}
fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)

fun getResourceId(context: Context, nameOfRes: String): Int {
    return context.resources.getIdentifier(nameOfRes, "drawable", context.packageName )
}

fun imageFromBase64(base64: String): Bitmap {
    val imageBytes = Base64Utils.decode(base64)
    return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.count())
}

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

fun Activity.goTo(destination: Class<*>) {
    this.startActivity(Intent(this, destination))
}

fun Activity.goToAndCloseOther(destination: Class<*>) {
    this.finishAffinity()
    this.startActivity(Intent(this, destination))
}