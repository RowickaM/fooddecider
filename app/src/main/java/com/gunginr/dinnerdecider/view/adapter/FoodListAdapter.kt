package com.gunginr.dinnerdecider.view.adapter

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.gunginr.dinnerdecider.R
import com.gunginr.dinnerdecider.util.toEditable

class FoodListAdapter(
    private val context: Context,
    private val foods: List<String>,
    private val removeClick: (position: Int) -> Unit,
    private val editClick: (position: Int, name: String) -> Unit
) :
    RecyclerView.Adapter<FoodListAdapter.ViewHolder>() {

    inner class ViewHolder(
        itemView: View,
        val remove: (position: Int) -> Unit,
        val edit: (position: Int, name: String) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {
        private val name = itemView.findViewById<EditText>(R.id.name)
        private val removeBtn = itemView.findViewById<ImageView>(R.id.remove)
        private val editBtn = itemView.findViewById<ImageView>(R.id.edit)

        fun bindFood(nameOfFood: String, position: Int) {
            name.text = nameOfFood.toEditable()
            editBtn.isEnabled = false

            removeBtn.setOnClickListener {
                removeBtn.isEnabled = false
                remove(position)
                removeBtn.isEnabled = true
            }

            name.addTextChangedListener(object : TextWatcher{
                override fun afterTextChanged(s: Editable?) {}

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    editBtn.isEnabled  = s.toString() != nameOfFood
                }

            })

            editBtn.setOnClickListener {
                editBtn.isEnabled = false
                edit(position, name.text.toString())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.item_food, parent, false)
        return ViewHolder(view, removeClick, editClick)
    }

    override fun getItemCount(): Int {
        return foods.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindFood(foods[position], position)
    }
}