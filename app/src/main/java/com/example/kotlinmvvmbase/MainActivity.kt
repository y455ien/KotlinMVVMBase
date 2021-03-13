package com.example.kotlinmvvmbase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.kotlinmvvmbase.core.base.BaseActivity
import com.example.kotlinmvvmbase.core.base.BaseCommunicator
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
                    if (BaseCommunicator.isLoading.value == true) binding.progressBar.visibility =
                            View.GONE
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