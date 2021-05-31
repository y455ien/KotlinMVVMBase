package com.example.kotlinmvvmbase.modules.business

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.kotlinmvvmbase.R
import com.example.kotlinmvvmbase.core.base.navigation.Destination
import com.example.kotlinmvvmbase.core.base.navigation.NavigationComponent
import com.example.kotlinmvvmbase.core.base.ui.BaseActivity
import com.example.kotlinmvvmbase.databinding.ActivityMainBinding

class BusinessActivity : BaseActivity<ActivityMainBinding, BusinessActivityVM>(ActivityMainBinding::inflate), NavigationComponent {

    override val navController: NavController
        get() = findNavController(R.id.fragment)

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
    }

    override fun initViewModel(): BusinessActivityVM {
        return ViewModelProvider(this).get(BusinessActivityVM::class.java)
    }

    override fun navigate(destination: Destination?) {
        destination?.let { destination ->
            navController.currentDestination?.getAction(destination.getActionId())?.let {
                navController.navigate(destination.destination, destination.navOptions)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
}