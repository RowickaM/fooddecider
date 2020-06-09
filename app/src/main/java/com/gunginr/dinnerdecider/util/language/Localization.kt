package com.gunginr.dinnerdecider.util.language

import android.content.Context
import java.util.*

object Localization {
    fun applyLanguage(context: Context, language: String) : Context{
        val locale = Locale(language)
        val configuration = context.resources.configuration

        Locale.setDefault(locale)
        configuration.setLocale(locale)
        return context.createConfigurationContext(configuration)
    }

}