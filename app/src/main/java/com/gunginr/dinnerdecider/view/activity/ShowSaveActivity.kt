package com.gunginr.dinnerdecider.view.activity

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.gunginr.dinnerdecider.R
import com.gunginr.dinnerdecider.base.BaseActivity
import com.gunginr.dinnerdecider.util.snackbars.createErrorSnackBar
import com.gunginr.dinnerdecider.util.storagedata.readFromSharedPref
import com.gunginr.dinnerdecider.util.storagedata.writeToSharedPref
import com.gunginr.dinnerdecider.view.adapter.FoodListAdapter
import kotlinx.android.synthetic.main.activity_show_save.*

class ShowSaveActivity : BaseActivity() {

    lateinit var adapter: FoodListAdapter
    lateinit var list: ArrayList<String>
    var listChanged: ArrayList<Boolean> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_save)

        list = readFromSharedPref(this)
        for (i in 0 until list.count()) {
            listChanged.add(false)
        }

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
            { i, name -> editClick(i, name) },
            { i, isChange -> onChangeTextInput(i, isChange) }
        )
    }

    private fun onChangeTextInput(position: Int, isChange: Boolean) {
        if (listChanged[position] != isChange) {
            listChanged.removeAt(position)
            listChanged.add(position, isChange)
        }
        if (!isChange) {
            listChanged.removeAt(position)
            listChanged.add(position, false)
        }
    }

    private fun wasChange(): Boolean {
        for (change in listChanged) {
            if (change) return true
        }
        return false
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
        onChangeTextInput(position, false)
        writeToSharedPref(this, list)
        bindList()

    }


    override fun onBackPressed() {
        if (!wasChange()) {
            super.onBackPressed()
        } else {
            createErrorSnackBar(
                this,
                getString(R.string.change_doesnt_save),
                getString(R.string.back)
            ) {
                super.onBackPressed()
            }.show()
        }
    }

}
