package com.gunginr.dinnerdecider.view.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gunginr.dinnerdecider.R
import com.gunginr.dinnerdecider.model.FoodCategory
import com.gunginr.dinnerdecider.util.imageFromBase64

class FoodCategoryAdapter(val activity: Activity, val list: ArrayList<FoodCategory>) :
    RecyclerView.Adapter<FoodCategoryAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val image = itemView.findViewById<ImageView>(R.id.imageCategory)
        private val name = itemView.findViewById<TextView>(R.id.nameCategory)
        private val tags = itemView.findViewById<RecyclerView>(R.id.tagsList)

        fun bindView(foodCategory: FoodCategory) {
            name.text = foodCategory.name
            tags.adapter = TagCategoryAdapter(activity, foodCategory.tags)
            tags.layoutManager =
                LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            if (foodCategory.image == "") {
                image.setImageDrawable(activity.resources.getDrawable(R.drawable.ic_food_and_restaurant_dark))
            } else {
                image.setImageBitmap(imageFromBase64(foodCategory.image))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(activity)
            .inflate(R.layout.item_food_category, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(list[position])
    }
}