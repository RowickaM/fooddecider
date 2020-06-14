package com.gunginr.dinnerdecider.base

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.gunginr.dinnerdecider.services.firebase.FirebaseFirestore
import com.gunginr.dinnerdecider.util.language.Localization
import com.gunginr.dinnerdecider.util.storagedata.Language

open class BaseActivity : AppCompatActivity() {
    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(
            Localization.applyLanguage(
                newBase,
                Language.getCurrentLanguage(newBase)
            )
        )
    }

    override fun onPause() {
        super.onPause()
        FirebaseFirestore.removeListeners()
    }
}