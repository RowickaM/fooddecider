package com.gunginr.dinnerdecider.view.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import com.gunginr.dinnerdecider.R
import com.gunginr.dinnerdecider.base.BaseActivity
import com.gunginr.dinnerdecider.services.firebase.FirebaseAuthorisation
import com.gunginr.dinnerdecider.services.firebase.FirebaseFirestore.dishesCollectionListener
import com.gunginr.dinnerdecider.util.goToAndCloseOther
import com.gunginr.dinnerdecider.util.handlers.HandleBundle
import com.gunginr.dinnerdecider.util.hideKeyboard
import com.gunginr.dinnerdecider.util.isExist
import com.gunginr.dinnerdecider.util.navigation.AppToolbar
import com.gunginr.dinnerdecider.util.snackbars.createErrorSnackBar
import com.gunginr.dinnerdecider.util.snackbars.createInfoSnackBar
import com.gunginr.dinnerdecider.util.storagedata.writeToSharedPref
import com.gunginr.dinnerdecider.util.variables.LANGUAGE_BUNDLE_SUCCESS
import kotlinx.android.synthetic.main.activity_decide.*
import kotlin.random.Random

class DecideActivity : BaseActivity() {

    private lateinit var listOfFood: ArrayList<String>
    private var doubleClick = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_decide)

        if (!FirebaseAuthorisation.isAuth()) {
            this.goToAndCloseOther(LoginActivity::class.java)
        }

        fillData()

        val toolbar = AppToolbar(this, rootView)
        toolbar.logo.visibility = View.GONE

        newFoodButton.setOnClickListener { addNewFood() }

        decideButton.setOnClickListener { deciderBtn() }
        toList.setOnClickListener {
            startActivity(Intent(this, ShowSaveActivity::class.java))
        }

        HandleBundle.handleBundleInformation(this, LANGUAGE_BUNDLE_SUCCESS)
    }

    private fun addNewFood() {
        val newFood = newFoodEditText.text.toString()
        rootView.hideKeyboard()
        when {
            newFood.trim() == "" -> {
                createInfoSnackBar(
                    this,
                    getString(R.string.empty_string)
                ).show()
            }
            isExist(newFood, listOfFood) -> {
                createErrorSnackBar(
                    this,
                    getString(R.string.already_added)
                ).show()
            }
            else -> {
                addToList(newFood)
            }
        }
    }

    private fun addToList(stringFood: String) {
        newFoodButton.isEnabled = false
        newFoodEditText.text?.clear()

        listOfFood.add(stringFood)
        writeToSharedPref(
            this,
            listOfFood
        )

        decideButton.isEnabled = true
        decideButton.setBackgroundColor(ContextCompat.getColor(this, R.color.grey))
        decideButton.setTextColor(ContextCompat.getColor(this, R.color.greenDark))

        newFoodButton.isEnabled = true
    }


    fun deciderBtn() {
        decideButton.isEnabled = false
        val index = Random.nextInt(listOfFood.count())
        result.text = listOfFood[index]
        decideButton.isEnabled = true
    }

    private fun fillData() {
        dishesCollectionListener(this) {
            listOfFood = it
            writeToSharedPref(this, listOfFood)

            if (listOfFood.count() == 0) {
                decideButton.isEnabled = false
                decideButton.setBackgroundColor(ContextCompat.getColor(this, R.color.greyLight))
                decideButton.setTextColor(ContextCompat.getColor(this, R.color.grey))
            }
        }
    }

    override fun onBackPressed() {
        if (doubleClick) {
            super.onBackPressed()
        } else {
            doubleClick = true
            val snackbar = createInfoSnackBar(
                this,
                getString(R.string.do_you_have_dinner),
                getString(R.string.i_go_out)
            ) {
                super.onBackPressed()
            }
            snackbar.show()
            Handler().postDelayed({ snackbar.dismiss(); doubleClick = false }, 1000)

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
