package com.gunginr.dinnerdecider.util.navigation

import android.app.Activity
import android.content.Intent
import android.view.MenuItem
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.gunginr.dinnerdecider.R
import com.gunginr.dinnerdecider.view.activity.ChangeLanguageActivity
import com.gunginr.dinnerdecider.view.activity.DecideActivity
import com.gunginr.dinnerdecider.view.activity.ShowSaveActivity

class MainNavigationListener(val activity: Activity, private val drawerLayout: DrawerLayout) :
    NavigationView.OnNavigationItemSelectedListener {
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_nav_home -> {
                if (activity is DecideActivity) {
                    closeNavigation()
                    return true
                }
                finishAffinity(activity)
                activity.startActivity(Intent(activity, DecideActivity::class.java))
                return true
            }
            R.id.item_nav_list -> {
                if (activity is ShowSaveActivity) {
                    closeNavigation()
                    return true
                }
                activity.startActivity(Intent(activity, ShowSaveActivity::class.java))

                return true
            }
            R.id.item_nav_change_language -> {
                if(activity is ChangeLanguageActivity){
                    closeNavigation()
                    return true
                }
                activity.startActivity(Intent(activity, ChangeLanguageActivity::class.java))
                return true
            }
        }
        return false
    }

    private fun closeNavigation(){
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        }
    }
}