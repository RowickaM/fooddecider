package com.gunginr.dinnerdecider.view.activity

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.gunginr.dinnerdecider.R
import com.gunginr.dinnerdecider.base.BaseActivity
import com.gunginr.dinnerdecider.util.storagedata.Language
import com.gunginr.dinnerdecider.util.storagedata.LanguageShort
import com.gunginr.dinnerdecider.util.storagedata.Languages
import com.gunginr.dinnerdecider.view.adapter.LanguageSelectorAdapter
import kotlinx.android.synthetic.main.activity_change_language.*

class ChangeLanguageActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_language)

        val adapter = LanguageSelectorAdapter(this, Languages(this).languages) { lang ->
            run {
                when (lang) {
                    LanguageShort.ENG -> Language.saveLanguage(this, "en")
                    LanguageShort.PL -> Language.saveLanguage(this, "pl")
                }
            }
        }
        listOfLanguage.adapter = adapter
        listOfLanguage.layoutManager = LinearLayoutManager(this)
    }

}


