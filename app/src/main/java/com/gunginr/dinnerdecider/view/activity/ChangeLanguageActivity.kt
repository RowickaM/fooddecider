package com.gunginr.dinnerdecider.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.gunginr.dinnerdecider.R
import com.gunginr.dinnerdecider.util.storagedata.Languages
import com.gunginr.dinnerdecider.view.adapter.LanguageSelectorAdapter
import kotlinx.android.synthetic.main.activity_change_language.*

class ChangeLanguageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_language)

        val adapter = LanguageSelectorAdapter(this, Languages(this).languages)
        list_of_language.adapter = adapter
        list_of_language.layoutManager = LinearLayoutManager(this)
    }
}
