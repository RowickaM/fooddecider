package com.gunginr.dinnerdecider.util.storagedata

import android.app.Activity
import com.gunginr.dinnerdecider.R
import com.gunginr.dinnerdecider.util.language.Language

class Languages(val activity: Activity) {
    val languages = arrayListOf<Language>(
        Language(activity.getString(R.string.english), "ic_english"),
        Language(activity.getString(R.string.polish), "ic_polish")
    )
}