package com.gunginr.dinnerdecider.util

import android.graphics.Color
import android.view.View
import com.google.android.material.snackbar.Snackbar
import timber.log.Timber
import java.util.*
import kotlin.collections.ArrayList

fun isExist(name: String, list: ArrayList<String>): Boolean {
    return list.find { it.toUpperCase(Locale.getDefault()) == name.toUpperCase(Locale.getDefault()) } != null
}