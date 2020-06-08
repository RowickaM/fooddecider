package com.gunginr.dinnerdecider.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.GravityCompat
import com.gunginr.dinnerdecider.R
import com.gunginr.dinnerdecider.util.*
import com.gunginr.dinnerdecider.util.navigation.AppToolbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var listOfFood: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fillData()

        val toolbar = AppToolbar(this, rootView)
        toolbar.logo.visibility = View.GONE

        newFoodButton.setOnClickListener(this::addNewFood)

        toList.setOnClickListener{
            startActivity(Intent(this, ShowSaveActivity::class.java))
        }
    }

    private fun addNewFood(view: View) {
        val newFood = newFoodEditText.text.toString();

        if (newFood.trim() == "") {
            createInfoSnackBar(
                this,
                getString(R.string.empty_string),
                getString(android.R.string.ok)
            ) {
            }
        } else if (isExist(newFood, listOfFood)) {
            createErrorSnackBar(this, getString(R.string.already_added))
        } else {
            addToList(newFood);
        }
    }

    private fun addToList(stringFood: String){
        newFoodButton.isEnabled = false
        newFoodEditText.text.clear();

        listOfFood.add(stringFood)
        writeToSharedPref(this, listOfFood)

        decideButton.isEnabled = true
        decideButton.setBackgroundColor(resources.getColor(R.color.grey));

        newFoodButton.isEnabled = true
    }



    fun deciderBtn(view: View) {
        decideButton.isEnabled = false
        val index = Random.nextInt(listOfFood.count());
        result.text = listOfFood[index]
        decideButton.isEnabled = true
    }

    private fun fillData() {
        listOfFood = readFromSharedPref(this)

        if (listOfFood.count() == 0) {
            decideButton.isEnabled = false
            decideButton.setBackgroundColor(resources.getColor(R.color.greyLight));
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
