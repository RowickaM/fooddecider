package com.gunginr.dinnerdecider.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.text.Editable
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.gms.common.util.Base64Utils
import com.gunginr.dinnerdecider.R
import java.util.*

private const val EMAIL_PATTERN = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
private const val PASSWORD_PATTERN =
    "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*.?])(?=\\S+$).{6,}$"

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

fun Activity.goToAndCloseLast(destination: Class<*>) {
    this.finish()
    this.startActivity(Intent(this, destination))
}

fun Activity.goToAndCloseOther(destination: Class<*>) {
    this.finishAffinity()
    this.startActivity(Intent(this, destination))
}

fun TextView.setBtnEnabled(activity: Activity, enable: Boolean) {
    if (enable) {
        this.isEnabled = true
        this.setBackgroundColor(ContextCompat.getColor(activity, R.color.grey))
        this.setTextColor(ContextCompat.getColor(activity, R.color.greenDark))
    } else {
        this.isEnabled = false
        this.setBackgroundColor(ContextCompat.getColor(activity, R.color.greyLight))
        this.setTextColor(ContextCompat.getColor(activity, R.color.grey))
    }
}

fun isEmailValid(email: String): Boolean {
    return if (EMAIL_PATTERN.toRegex().matches(email)) {
        Log.d("VALIDATOR", "email is valid")
        true
    } else {
        Log.d("VALIDATOR", "email is not valid")
        false
    }
}

fun isPasswordValid(password: String): Boolean {
    return if (PASSWORD_PATTERN.toRegex().matches(password)) {
        Log.d("VALIDATOR", "password is valid")
        true
    } else {
        Log.d("VALIDATOR", "password is not valid")
        false
    }
}