package com.gunginr.dinnerdecider.view.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gunginr.dinnerdecider.R

class TagCategoryAdapter(val activity: Activity, val tags: ArrayList<String>) :
    RecyclerView.Adapter<TagCategoryAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tagName = itemView.findViewById<TextView>(R.id.tagName)
        fun bindView(name: String) {
            tagName.text = name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(activity)
            .inflate(R.layout.item_tag_food_category, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return tags.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(tags[position])
    }
}