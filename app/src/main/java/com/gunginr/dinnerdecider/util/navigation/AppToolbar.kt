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
    var logo: ImageView = activity.findViewById(R.id.logo)
    private var hamburgerBtn: ImageView = activity.findViewById(R.id.hamburgerBtn)
    private var navigationView: NavigationView

    init {
        logo.setOnClickListener {
            activity.startActivity(
                Intent(
                    activity,
                    DecideActivity::class.java
                )
            )
        }

        hamburgerBtn.setOnClickListener {
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