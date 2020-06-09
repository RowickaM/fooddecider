package com.gunginr.dinnerdecider.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.gunginr.dinnerdecider.R
import com.gunginr.dinnerdecider.view.adapter.FoodListAdapter
import com.gunginr.dinnerdecider.util.snackbars.createInfoSnackBar
import com.gunginr.dinnerdecider.util.storagedata.readFromSharedPref
import com.gunginr.dinnerdecider.util.storagedata.writeToSharedPref
import kotlinx.android.synthetic.main.activity_show_save.*

class ShowSaveActivity : AppCompatActivity() {

    lateinit var adapter: FoodListAdapter
    lateinit var list: ArrayList<String>
    private var doubleBackPress = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_save)

        list =
            readFromSharedPref(this)

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
        adapter = FoodListAdapter(
            this,
            list,
            { removeClick(it) },
            { i, name -> editClick(i, name) }
        )
    }

    private fun removeClick(position: Int) {
        list.removeAt(position)
        writeToSharedPref(this, list)
        adapter.notifyDataSetChanged()

        if (list.size == 0) {
            showList(false)
        }
    }

    private fun editClick(position: Int, name: String) {
        list.removeAt(position)
        list.add(position, name)
        writeToSharedPref(this, list)
        adapter.notifyDataSetChanged()
    }

    override fun onBackPressed() {
        if (doubleBackPress) {
            super.onBackPressed()
        }
        createInfoSnackBar(
            this,
            getString(R.string.change_doesnt_save)
        )
        doubleBackPress = true
        Handler().postDelayed({ doubleBackPress = false }, 1500)
    }

}
