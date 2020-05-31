package com.gunginr.dinnerdecider.controller

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.gunginr.dinnerdecider.R
import com.gunginr.dinnerdecider.util.SHARED_PREF_KEY
import com.gunginr.dinnerdecider.util.readFromSharedPref
import com.gunginr.dinnerdecider.util.writeToSharedPref
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var listOfFood: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listOfFood = readFromSharedPref(this)?: arrayListOf()

        if (listOfFood.count() == 0){
            decideButton.isEnabled = false
            decideButton.setBackgroundColor(resources.getColor(R.color.greyLight));
        }


        newFoodButton.setOnClickListener{
            val newFood = newFoodEditText.text.toString();

            newFoodButton.isEnabled = false
            newFoodEditText.text.clear();

            listOfFood.add(newFood)
            writeToSharedPref(this, listOfFood)

            decideButton.isEnabled = true
            decideButton.setBackgroundColor(resources.getColor(R.color.greenDark));

            newFoodButton.isEnabled = true
        }
        toList.setOnClickListener {
            startActivity(Intent(this, ShowSaveActivity::class.java))
        }
    }


   fun deciderBtn(view: View){
        decideButton.isEnabled = false
        val index = Random.nextInt(listOfFood.count());
        result.text = listOfFood[index]
        decideButton.isEnabled = true
    }

}
