package com.gunginr.dinnerdecider.util.snackbars

import android.app.Activity
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.gunginr.dinnerdecider.R


fun createErrorSnackBar(context: Activity, message: String): Snackbar {
    val snackbar = Snackbar.make(
        context.findViewById(android.R.id.content),
        message,
        Snackbar.LENGTH_SHORT
    )

    snackbar.setBackgroundTint(ContextCompat.getColor(context, R.color.red))
    snackbar.setTextColor(ContextCompat.getColor(context, R.color.white))
    return snackbar
}

fun createErrorSnackBar(
    context: Activity,
    message: String,
    actionText: String,
    action: () -> Unit
): Snackbar {
    val snackbar = Snackbar.make(
        context.findViewById(android.R.id.content),
        message,
        Snackbar.LENGTH_SHORT
    ).setAction(actionText) { action() }

    snackbar.setBackgroundTint(ContextCompat.getColor(context, R.color.red))
    snackbar.setTextColor(ContextCompat.getColor(context, R.color.white))
    snackbar.setActionTextColor(ContextCompat.getColor(context, R.color.white))
    return snackbar
}

fun createSuccessSnackBar(context: Activity, message: String): Snackbar {
    val snackbar = Snackbar.make(
        context.findViewById(android.R.id.content),
        message,
        Snackbar.LENGTH_SHORT
    )

    snackbar.setBackgroundTint(ContextCompat.getColor(context, R.color.green))
    snackbar.setTextColor(ContextCompat.getColor(context, R.color.white))
    return snackbar
}

fun createInfoSnackBar(
    context: Activity,
    message: String,
    actionText: String,
    action: () -> Unit
): Snackbar {
    val snackbar = Snackbar.make(
        context.findViewById(android.R.id.content),
        message,
        Snackbar.LENGTH_INDEFINITE
    ).setAction(actionText) { action() }

    snackbar.setBackgroundTint(ContextCompat.getColor(context, R.color.grey))
    snackbar.setTextColor(ContextCompat.getColor(context, R.color.white))
    snackbar.setActionTextColor(ContextCompat.getColor(context, R.color.white))
    return snackbar
}


fun createInfoSnackBar(context: Activity, message: String): Snackbar {
    val snackbar = Snackbar.make(
        context.findViewById(android.R.id.content),
        message,
        Snackbar.LENGTH_SHORT
    )

    snackbar.setBackgroundTint(ContextCompat.getColor(context, R.color.grey))
    snackbar.setTextColor(ContextCompat.getColor(context, R.color.white))
    return snackbar
}