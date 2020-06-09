package com.gunginr.dinnerdecider.view.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gunginr.dinnerdecider.R
import com.gunginr.dinnerdecider.util.getResourceId
import com.gunginr.dinnerdecider.util.language.Language

class LanguageSelectorAdapter(
    val activity: Activity,
    val list: ArrayList<Language>
): RecyclerView.Adapter<LanguageSelectorAdapter.ViewHolder>(){

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val imageView: ImageView = itemView.findViewById<ImageView>(R.id.image)
        private val textView: TextView = itemView.findViewById<TextView>(R.id.name)
        fun bindView(language: Language){
            textView.text=language.name
            imageView.setImageResource(getResourceId(activity, language.image))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(activity)
            .inflate(R.layout.item_language, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return list.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(list[position])
    }
}