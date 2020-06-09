package com.gunginr.dinnerdecider.util.handlers

import android.app.Activity
import com.gunginr.dinnerdecider.util.snackbars.createInfoSnackBar

object HandleBundle {
    fun handleBundleInformation(activity: Activity, key: String){
        val bundle = activity.intent;
        if(bundle != null){
            val message = bundle.getStringExtra(key)
            if ( message != null && message != ""){
                createInfoSnackBar(activity, message)
            }
        }
    }
}