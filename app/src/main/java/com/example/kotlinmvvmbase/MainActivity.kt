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
import com.example.kotlinmvvmbase.core.base.BaseCommunicator
import com.example.kotlinmvvmbase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var listener: NavController.OnDestinationChangedListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setupNavController()
        setupNavControllerListener()
        setupAppBar()
        setupBottomNav()
        observeCommunicator()
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
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    private fun setupBottomNav() {
        binding.bottomNavigationView.setupWithNavController(navController)
    }

    private fun observeCommunicator() {
        BaseCommunicator.isLoading.observe(
            this,
            { isLoading ->
                binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            })
        BaseCommunicator.toast.observe(
            this,
            { message -> Toast.makeText(this, message, Toast.LENGTH_LONG).show() })
        BaseCommunicator.navigation.observe(
            this,
            { navigationId -> navController.navigate(navigationId) })
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onResume() {
        super.onResume()
        navController.addOnDestinationChangedListener(listener)
    }

    override fun onPause() {
        navController.removeOnDestinationChangedListener(listener)
        super.onPause()
    }
}