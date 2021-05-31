package com.example.kotlinmvvmbase.core.base.navigation

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.kotlinmvvmbase.R
import com.google.android.material.bottomnavigation.BottomNavigationView

interface NavigationComponent {
    val navController: NavController

    fun setupNavComponent(
            activity: AppCompatActivity,
            topDestinationSet: Set<Int>? = null,
            navListener: NavController.OnDestinationChangedListener? = null,
            bottomNav: BottomNavigationView? = null,
    ) {
        topDestinationSet?.let {
            setupAppBar(activity)
        }
        navListener?.let {
            setupNavListener(it)
        }
        bottomNav?.let {
            setupBottomNavView(it)
        }
    }

    fun setupBottomNavView(bottomNav: BottomNavigationView) {
        bottomNav.setupWithNavController(this.navController)
    }

    fun setupNavListener(navListener: NavController.OnDestinationChangedListener) {
        navController.addOnDestinationChangedListener(navListener)
    }

    private fun setupAppBar(activity: AppCompatActivity) {
        val appBarConfiguration = AppBarConfiguration(
                setOf(
                        R.id.homeFragment,
                        R.id.profileFragment,
                        R.id.settingsFragment
                )
        )
        setupActionBarWithNavController(activity, navController, appBarConfiguration)
    }

    fun navigate(destination: Destination) {
        navController.currentDestination?.getAction(destination.getActionId())?.let {
            navController.navigate(destination.destination, destination.navOptions)
        }
    }

}