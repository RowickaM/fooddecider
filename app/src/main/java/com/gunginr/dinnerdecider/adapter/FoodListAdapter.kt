package com.gunginr.dinnerdecider.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gunginr.dinnerdecider.R

class FoodListAdapter(
    private val context: Context,
    private val foods: List<String>,
    private val removeClick: (position: Int) -> Unit
) :
    RecyclerView.Adapter<FoodListAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View, val remove: (position: Int) -> Unit) : RecyclerView.ViewHolder(itemView) {
        private val name = itemView.findViewById<TextView>(R.id.name)
        private val removeBtn = itemView.findViewById<ImageView>(R.id.remove)

        fun bindFood(nameOfFood: String, position: Int) {
            name.text = nameOfFood
            removeBtn.setOnClickListener {
                removeBtn.isEnabled = false
                remove(position)
                removeBtn.isEnabled = true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.food_item, parent, false)
        return ViewHolder(view, removeClick)
    }

    override fun getItemCount(): Int {
        return foods.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindFood(foods[position], position)
    }
}