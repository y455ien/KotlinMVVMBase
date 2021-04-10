package com.example.kotlinmvvmbase.modules.business

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.kotlinmvvmbase.R
import com.example.kotlinmvvmbase.core.base.navigation.NavDestinationWrapper
import com.example.kotlinmvvmbase.core.base.ui.BaseActivity
import com.example.kotlinmvvmbase.core.base.ui.NavComponent
import com.example.kotlinmvvmbase.databinding.ActivityMainBinding

class BusinessActivity : BaseActivity<ActivityMainBinding, BusinessActivityVM>(ActivityMainBinding::inflate), NavComponent {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupNavComponent(
                activity = this,
                topDestinationSet = setOf(R.id.homeFragment, R.id.profileFragment, R.id.settingsFragment),
                navListener = { _, destination, _ ->
                    binding.progressBar.visibility = View.GONE
                    when (destination.id) {
                        R.id.homeFragment,
                        R.id.profileFragment,
                        R.id.settingsFragment -> binding.bottomNavigationView.visibility = View.VISIBLE
                        else -> binding.bottomNavigationView.visibility = View.GONE
                    }
                },
                bottomNav = binding.bottomNavigationView,
        )
//        setupNavController()
//        setupNavControllerListener()
//        setupAppBar()
//        setupBottomNav()
    }

//    private fun setupNavControllerListener() {
//        listener =
//                NavController.OnDestinationChangedListener { controller, destination, arguments ->
//                    binding.progressBar.visibility = View.GONE
//                    when (destination.id) {
//                        R.id.homeFragment,
//                        R.id.profileFragment,
//                        R.id.settingsFragment -> binding.bottomNavigationView.visibility = View.VISIBLE
//                        else -> binding.bottomNavigationView.visibility = View.GONE
//                    }
//                }
//    }

//    private fun setupNavController() {
////        navController = findNavController(R.id.fragment)
//    }

//    private fun setupAppBar() {
//        val appBarConfiguration = AppBarConfiguration(
//                setOf(
//                        R.id.homeFragment,
//                        R.id.profileFragment,
//                        R.id.settingsFragment
//                )
//        )
//        navController?.let { setupActionBarWithNavController(navController!!, appBarConfiguration) }
//    }

//    private fun setupBottomNav() {
//        navController?.let { binding.bottomNavigationView.setupWithNavController(navController!!) }
//    }

    override fun initViewModel(): BusinessActivityVM {
        return ViewModelProvider(this).get(BusinessActivityVM::class.java)
    }

    override val navController: NavController
        get() = findNavController(R.id.fragment)

    override fun navigate(navDestinationWrapper: NavDestinationWrapper?) {
        if (navDestinationWrapper != null) {
            if (navController.currentDestination?.getAction(navDestinationWrapper.destination.actionId) != null) {
                navController.navigate(navDestinationWrapper.destination, navDestinationWrapper.navOptions)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
}