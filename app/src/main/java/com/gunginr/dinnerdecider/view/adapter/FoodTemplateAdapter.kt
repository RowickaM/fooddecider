package com.gunginr.dinnerdecider.view.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gunginr.dinnerdecider.R
import com.gunginr.dinnerdecider.model.DishesTemplate

class FoodTemplateAdapter(
    val activity: Activity,
    val list: ArrayList<DishesTemplate>,
    val add: (Int) -> Unit,
    val remove: (Int) -> Unit
) : RecyclerView.Adapter<FoodTemplateAdapter.ViewHolder>() {


    inner class ViewHolder(
        itemView: View,
        val addToList: (Int) -> Unit,
        val removeFromList: (Int) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {
        private val nameTV = itemView.findViewById<TextView>(R.id.name)
        private val addSwitch = itemView.findViewById<Switch>(R.id.addSwitch)

        fun bindView(item: DishesTemplate, position: Int) {
            nameTV.text = item.name

            addSwitch.setOnCheckedChangeListener(null)
            addSwitch.isChecked = item.add
            addSwitch.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    addToList(position)
                } else {
                    removeFromList(position)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(activity)
            .inflate(R.layout.item_food_template, parent, false)
        return ViewHolder(view, add, remove)
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(list[position], position)
    }
}