package com.gunginr.dinnerdecider.view.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import com.gunginr.dinnerdecider.R
import com.gunginr.dinnerdecider.util.*
import com.gunginr.dinnerdecider.util.handlers.HandleBundle
import com.gunginr.dinnerdecider.util.language.Localization
import com.gunginr.dinnerdecider.util.navigation.AppToolbar
import com.gunginr.dinnerdecider.util.snackbars.createErrorSnackBar
import com.gunginr.dinnerdecider.util.snackbars.createInfoSnackBar
import com.gunginr.dinnerdecider.util.storagedata.Language.getCurrentLanguage
import com.gunginr.dinnerdecider.util.storagedata.readFromSharedPref
import com.gunginr.dinnerdecider.util.storagedata.writeToSharedPref
import com.gunginr.dinnerdecider.util.variables.LANGUAGE_BUNDLE_SUCCESS
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class DecideActivity : AppCompatActivity() {

    private lateinit var listOfFood: ArrayList<String>

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(Localization.applyLanguage(newBase, getCurrentLanguage(newBase)))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        HandleBundle.handleBundleInformation(this, LANGUAGE_BUNDLE_SUCCESS)

        fillData()

        val toolbar = AppToolbar(this, rootView)
        toolbar.logo.visibility = View.GONE

        newFoodButton.setOnClickListener { addNewFood() }

        decideButton.setOnClickListener { deciderBtn() }
        toList.setOnClickListener {
            startActivity(Intent(this, ShowSaveActivity::class.java))
        }
    }

    private fun addNewFood() {
        val newFood = newFoodEditText.text.toString()

        when {
            newFood.trim() == "" -> {
                createInfoSnackBar(
                    this,
                    getString(R.string.empty_string)
                )
            }
            isExist(newFood, listOfFood) -> {
                createErrorSnackBar(
                    this,
                    getString(R.string.already_added)
                )
            }
            else -> {
                addToList(newFood)
            }
        }
    }

    private fun addToList(stringFood: String) {
        newFoodButton.isEnabled = false
        newFoodEditText.text.clear()

        listOfFood.add(stringFood)
        writeToSharedPref(
            this,
            listOfFood
        )

        decideButton.isEnabled = true
        decideButton.setBackgroundColor(ContextCompat.getColor(this, R.color.grey))

        newFoodButton.isEnabled = true
    }


    fun deciderBtn() {
        decideButton.isEnabled = false
        val index = Random.nextInt(listOfFood.count())
        result.text = listOfFood[index]
        decideButton.isEnabled = true
    }

    private fun fillData() {
        listOfFood =
            readFromSharedPref(this)

        if (listOfFood.count() == 0) {
            decideButton.isEnabled = false
            decideButton.setBackgroundColor(ContextCompat.getColor(this, R.color.greyLight))
        }
    }

    override fun onStop() {
        super.onStop()
        rootView.closeDrawer(GravityCompat.START)
    }

    override fun onRestart() {
        super.onRestart()
        fillData()
    }

}
