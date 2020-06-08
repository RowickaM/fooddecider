package com.gunginr.dinnerdecider.util.navigation

import android.app.Activity
import android.content.Intent
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.gunginr.dinnerdecider.R
import com.gunginr.dinnerdecider.view.activity.MainActivity
import com.gunginr.dinnerdecider.view.activity.ShowSaveActivity

class MainNavigationListener(val activity: Activity, val drawerLayout: DrawerLayout) :
    NavigationView.OnNavigationItemSelectedListener {
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_nav_home -> {
                if (activity is MainActivity) {
                    if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                        drawerLayout.closeDrawer(GravityCompat.START)
                    }
                    return true
                }
                val homeIntent=Intent(activity, MainActivity::class.java)
                activity.startActivity(homeIntent)
                return true
            }
            R.id.item_nav_list -> {
                if (activity is ShowSaveActivity) {
                    if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                        drawerLayout.closeDrawer(GravityCompat.START)
                    }
                    return true
                }
                val listIntent=Intent(activity, ShowSaveActivity::class.java)
                activity.startActivity(listIntent)

                return true
            }
            R.id.item_nav_change_language -> {
                return true
            }
        }
        return false;
    }
}