package com.gunginr.dinnerdecider.view.activity

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.gunginr.dinnerdecider.R
import com.gunginr.dinnerdecider.base.BaseActivity
import com.gunginr.dinnerdecider.model.FoodCategory
import com.gunginr.dinnerdecider.util.navigation.AppToolbar
import com.gunginr.dinnerdecider.view.adapter.FoodCategoryAdapter
import kotlinx.android.synthetic.main.activity_templates.*

class TemplatesActivity : BaseActivity() {

    lateinit var adater: FoodCategoryAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_templates)
        AppToolbar(this, rootView)

        listOfTemplates.layoutManager = LinearLayoutManager(this)

        adater = FoodCategoryAdapter(this, getList())
        listOfTemplates.adapter = adater

//        getTemplates{
//            adater = FoodCategoryAdapter(this, it)
//            listOfTemplates.adapter = adater
    }

    private fun getList(): ArrayList<FoodCategory> {
        return arrayListOf(
            FoodCategory(
                "Asia Food",
                "",
                arrayListOf(
                    "rice",
                    "chicken",
                    "rice",
                    "chicken",
                    "rice",
                    "chicken",
                    "rice",
                    "chicken",
                    "rice",
                    "chicken",
                    "rice",
                    "chicken"
                ),
                arrayListOf(
                    "rice with Chicken"
                )
            ),
            FoodCategory(
                "Street Food",
                "",
                arrayListOf(
                    "fast food",
                    "fat"
                ),
                arrayListOf(
                    "hot dog",
                    "burger"
                )
            )
        )
    }
}

