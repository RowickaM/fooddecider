package com.gunginr.dinnerdecider.controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.gunginr.dinnerdecider.R
import com.gunginr.dinnerdecider.adapter.FoodListAdapter
import com.gunginr.dinnerdecider.util.readFromSharedPref
import kotlinx.android.synthetic.main.activity_show_save.*

class ShowSaveActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_save)

        val listOfFood = readFromSharedPref(this)

        if (listOfFood != null) {
            if (listOfFood.count() == 0) {
                showList(false)
            } else {
                showList(true)
                foodList.adapter = bindList(listOfFood)
            }
        } else{
            showList(false)
        }

        foodList.layoutManager = LinearLayoutManager(this)
        foodList.setHasFixedSize(true)
    }

    private fun showList(show: Boolean){
        if (show){
            emptyListInfo.visibility = View.GONE
            foodList.visibility = View.VISIBLE
        }else{
            emptyListInfo.visibility = View.VISIBLE
            foodList.visibility = View.GONE
        }
    }

    fun bindList(list: List<String>): FoodListAdapter{
        return FoodListAdapter(this, list)
    }
}
