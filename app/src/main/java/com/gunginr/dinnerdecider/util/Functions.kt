package com.gunginr.dinnerdecider.util

import java.util.*
import kotlin.collections.ArrayList

fun isExist(name: String, list: ArrayList<String>): Boolean {
    return list.find { it.toUpperCase(Locale.getDefault()) == name.toUpperCase(Locale.getDefault()) } != null
}