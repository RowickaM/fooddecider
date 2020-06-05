package com.gunginr.dinnerdecider.controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.gunginr.dinnerdecider.R
import com.gunginr.dinnerdecider.adapter.FoodListAdapter
import com.gunginr.dinnerdecider.util.readFromSharedPref
import com.gunginr.dinnerdecider.util.writeToSharedPref
import kotlinx.android.synthetic.main.activity_show_save.*

class ShowSaveActivity : AppCompatActivity() {

    lateinit var adapter: FoodListAdapter
    lateinit var list: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_save)

        list = readFromSharedPref(this)

        if (list.size == 0) {
            showList(false)
        } else {
            showList(true)
            bindList()
            foodList.adapter = adapter
        }

        foodList.layoutManager = LinearLayoutManager(this)
        foodList.setHasFixedSize(true)
    }

    private fun showList(show: Boolean) {
        if (show) {
            emptyListInfo.visibility = View.GONE
            foodList.visibility = View.VISIBLE
        } else {
            emptyListInfo.visibility = View.VISIBLE
            foodList.visibility = View.GONE
        }
    }

    private fun bindList() {
        adapter = FoodListAdapter(this, list) { removeClick(it) }
    }

    private fun removeClick(position: Int) {
        list.removeAt(position)
        writeToSharedPref(this, list)
        adapter.notifyDataSetChanged()

        if(list.size == 0){
            showList(false)
        }
    }
}
