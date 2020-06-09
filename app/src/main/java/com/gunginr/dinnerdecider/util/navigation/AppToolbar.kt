package com.gunginr.dinnerdecider.util.navigation

import android.app.Activity
import android.content.Intent
import android.widget.ImageView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.gunginr.dinnerdecider.R
import com.gunginr.dinnerdecider.view.activity.DecideActivity

class AppToolbar(activity: Activity, drawerLayout: DrawerLayout) {
    var logo: ImageView
    var hamburger_btn: ImageView
    private lateinit var navigationView: NavigationView

    init {
        logo = activity.findViewById<ImageView>(R.id.logo)
        hamburger_btn = activity.findViewById<ImageView>(R.id.hamburger_btn)
        logo.setOnClickListener {
            activity.startActivity(
                Intent(
                    activity,
                    DecideActivity::class.java
                )
            )
        }

        hamburger_btn.setOnClickListener {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                drawerLayout.openDrawer(GravityCompat.START)
            }
        }

        navigationView = activity.findViewById(R.id.navigation)
        navigationView.setNavigationItemSelectedListener(MainNavigationListener(activity, drawerLayout))
    }


}