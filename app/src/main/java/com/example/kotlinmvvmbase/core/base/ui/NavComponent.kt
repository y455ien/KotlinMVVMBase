package com.example.kotlinmvvmbase.core.base.ui

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.kotlinmvvmbase.R
import com.google.android.material.bottomnavigation.BottomNavigationView

interface NavComponent {
    val navController: NavController

    fun setupNavComponent(
            activity: AppCompatActivity,
            topDestinationSet: Set<Int>? = null,
            navListener: NavController.OnDestinationChangedListener? = null,
            bottomNav: BottomNavigationView? = null,
    ) {
        topDestinationSet?.let {
            val appBarConfiguration = AppBarConfiguration(
                    setOf(
                            R.id.homeFragment,
                            R.id.profileFragment,
                            R.id.settingsFragment
                    )
            )
            setupActionBarWithNavController(activity, navController, appBarConfiguration)
        }
        navListener?.let {
            navController.addOnDestinationChangedListener(navListener)
        }

        bottomNav?.setupWithNavController(this.navController)
    }

    fun navigate() {

    }
}