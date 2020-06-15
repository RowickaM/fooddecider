package com.gunginr.dinnerdecider.view.activity.templates

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gunginr.dinnerdecider.R
import com.gunginr.dinnerdecider.model.DishesTemplate
import com.gunginr.dinnerdecider.model.FoodCategory
import com.gunginr.dinnerdecider.util.imageFromBase64
import com.gunginr.dinnerdecider.util.storagedata.readFromSharedPref
import com.gunginr.dinnerdecider.util.variables.DISHES_KEY
import com.gunginr.dinnerdecider.view.adapter.FoodAlreadyAddedAdapter
import com.gunginr.dinnerdecider.view.adapter.FoodTemplateAdapter
import com.gunginr.dinnerdecider.view.adapter.TagCategoryAdapter
import kotlinx.android.synthetic.main.activity_template_list.*

class TemplateListActivity : AppCompatActivity() {

    lateinit var adapterToAddList: FoodTemplateAdapter
    lateinit var adapterAlreadyAddedList: FoodAlreadyAddedAdapter
    lateinit var listToAdd: ArrayList<DishesTemplate>
    lateinit var listAlreadyAdded: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_template_list)

        toAddList.layoutManager = LinearLayoutManager(this)
        alreadyAddedList.layoutManager = GridLayoutManager(this, 3)

        val bundle = intent.extras

        if (bundle != null) {
            val template = bundle.getSerializable(DISHES_KEY) as FoodCategory
            setLists(template.dishes)
            setHeader(template)

            adapterToAddList = FoodTemplateAdapter(
                this,
                listToAdd,
                { id -> listToAdd[id].add = true },
                { id -> listToAdd[id].add = false }
            )

            adapterAlreadyAddedList = FoodAlreadyAddedAdapter(this, listAlreadyAdded)

            toAddList.adapter = adapterToAddList
            alreadyAddedList.adapter = adapterAlreadyAddedList
        }
    }

    private fun setHeader(item: FoodCategory) {
        val image = header.findViewById<ImageView>(R.id.imageCategory)
        val name = header.findViewById<TextView>(R.id.nameCategory)
        val tags = header.findViewById<RecyclerView>(R.id.tagsList)

        tags.adapter = TagCategoryAdapter(this, item.tags)
        tags.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        name.text = item.name
        if (item.image == "") {
            image.setImageDrawable(resources.getDrawable(R.drawable.ic_food_and_restaurant_dark))
        } else {
            image.setImageBitmap(imageFromBase64(item.image))
        }
    }

    private fun setLists(list: ArrayList<String>) {
        val existList = readFromSharedPref(this)

        val result = arrayListOf<DishesTemplate>()
        for (it in list) {
            result.add(DishesTemplate(it, false))
        }
        listToAdd = result.filterNot { existList.contains(it.name) } as ArrayList<DishesTemplate>
        listAlreadyAdded = list.filter { existList.contains(it) } as ArrayList<String>
    }
}
