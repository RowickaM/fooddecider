package com.gunginr.dinnerdecider.util

import android.app.Activity
import com.google.android.material.snackbar.Snackbar
import com.gunginr.dinnerdecider.R


fun createErrorSnackBar(
    context: Activity,
    message: String,
    actionText: String,
    action: () -> Unit
){
    val snackbar = Snackbar.make(
        context.findViewById(android.R.id.content),
        message,
        Snackbar.LENGTH_INDEFINITE
    ).setAction(actionText) { action() }

    snackbar.setBackgroundTint(context.resources.getColor(R.color.red))
    snackbar.setTextColor(context.resources.getColor(R.color.white))
    snackbar.setActionTextColor(context.resources.getColor(R.color.white))
    snackbar.show()
}

fun createErrorSnackBar(context: Activity, message: String) {
    val snackbar = Snackbar.make(
        context.findViewById(android.R.id.content),
        message,
        Snackbar.LENGTH_SHORT
    )

    snackbar.setBackgroundTint(context.resources.getColor(R.color.red))
    snackbar.setTextColor(context.resources.getColor(R.color.white))
    snackbar.show()
}

fun createSuccessSnackBar(
    context: Activity,
    message: String,
    actionText: String,
    action: () -> Unit
){
    val snackbar = Snackbar.make(
        context.findViewById(android.R.id.content),
        message,
        Snackbar.LENGTH_INDEFINITE
    ).setAction(actionText) { action() }

    snackbar.setBackgroundTint(context.resources.getColor(R.color.green))
    snackbar.setTextColor(context.resources.getColor(R.color.white))
    snackbar.setActionTextColor(context.resources.getColor(R.color.white))
    snackbar.show()
}

fun createSuccessSnackBar(context: Activity, message: String){
    val snackbar = Snackbar.make(
        context.findViewById(android.R.id.content),
        message,
        Snackbar.LENGTH_SHORT
    )

    snackbar.setBackgroundTint(context.resources.getColor(R.color.green))
    snackbar.setTextColor(context.resources.getColor(R.color.white))
    snackbar.show()

}

fun createInfoSnackBar(
    context: Activity,
    message: String,
    actionText: String,
    action: () -> Unit
){
    val snackbar = Snackbar.make(
        context.findViewById(android.R.id.content),
        message,
        Snackbar.LENGTH_INDEFINITE
    ).setAction(actionText) { action() }

    snackbar.setBackgroundTint(context.resources.getColor(R.color.grey))
    snackbar.setTextColor(context.resources.getColor(R.color.white))
    snackbar.setActionTextColor(context.resources.getColor(R.color.white))
    snackbar.show()
}


fun createInfoSnackBar(context: Activity, message: String) {
    val snackbar = Snackbar.make(
        context.findViewById(android.R.id.content),
        message,
        Snackbar.LENGTH_SHORT
    )

    snackbar.setBackgroundTint(context.resources.getColor(R.color.grey))
    snackbar.setTextColor(context.resources.getColor(R.color.white))
    snackbar.show()
}