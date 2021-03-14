package com.example.kotlinmvvmbase

import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.kotlinmvvmbase.core.base.ui.BaseActivity
import com.example.kotlinmvvmbase.util.BaseCommunicator
import com.example.kotlinmvvmbase.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupNavController()
        setupNavControllerListener()
        setupAppBar()
        setupBottomNav()
    }

    private fun setupNavControllerListener() {
        listener =
                NavController.OnDestinationChangedListener { controller, destination, arguments ->
                    binding.progressBar.visibility = View.GONE
                    when (destination.id) {
                        R.id.homeFragment, R.id.profileFragment, R.id.settingsFragment -> binding.bottomNavigationView.visibility = View.VISIBLE
                        else -> binding.bottomNavigationView.visibility = View.GONE
                    }
                }
    }

    private fun setupNavController() {
        navController = findNavController(R.id.fragment)
    }

    private fun setupAppBar() {
        val appBarConfiguration = AppBarConfiguration(
                setOf(
                        R.id.homeFragment,
                        R.id.profileFragment,
                        R.id.settingsFragment,
                )
        )
        navController?.let { setupActionBarWithNavController(navController!!, appBarConfiguration) }
    }

    private fun setupBottomNav() {
        navController?.let { binding.bottomNavigationView.setupWithNavController(navController!!) }
    }
}