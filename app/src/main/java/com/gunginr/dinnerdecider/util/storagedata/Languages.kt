package com.gunginr.dinnerdecider.util.storagedata

import android.app.Activity
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import com.gunginr.dinnerdecider.R
import com.gunginr.dinnerdecider.util.language.Language
import com.gunginr.dinnerdecider.util.variables.CURRENT_LANG_KEY
import com.gunginr.dinnerdecider.util.variables.LANGUAGE_BUNDLE_SUCCESS
import com.gunginr.dinnerdecider.util.variables.SHARED_PREF_KEY_LANGUAGE
import com.gunginr.dinnerdecider.view.activity.DecideActivity

enum class LanguageShort{
    ENG,
    PL
}

object Language{
    fun getCurrentLanguage(context: Context): String{
        val sharedPreferences = context.getSharedPreferences(SHARED_PREF_KEY_LANGUAGE, MODE_PRIVATE)
        val lang = sharedPreferences.getString(CURRENT_LANG_KEY, "")
        return if (lang == null || lang == ""){
            "pl"
        }else{
            lang
        }
    }
    fun saveLanguage(activity: Activity, lang: String){
        activity.getSharedPreferences(SHARED_PREF_KEY_LANGUAGE, MODE_PRIVATE).edit()
            .putString(CURRENT_LANG_KEY, lang)
            .commit()
        activity.finish()
        val homeIntent = Intent(activity, DecideActivity::class.java)
        when (lang) {
            "pl" -> homeIntent.putExtra(LANGUAGE_BUNDLE_SUCCESS, "Język został zmieniony")
            "en" -> homeIntent.putExtra(LANGUAGE_BUNDLE_SUCCESS, "The language has been changed")
        }

        activity.startActivity(homeIntent)
        activity.finishAffinity()
    }
}

class Languages(val activity: Activity) {
    val languages = arrayListOf<Language>(
        Language(activity.getString(R.string.english), "ic_english", LanguageShort.ENG),
        Language(activity.getString(R.string.polish), "ic_polish", LanguageShort.PL)
    )
}