package com.example.kotlinmvvmbase.modules.authentication

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.kotlinmvvmbase.R
import com.example.kotlinmvvmbase.core.base.navigation.NavDestinationWrapper
import com.example.kotlinmvvmbase.core.base.ui.BaseActivity
import com.example.kotlinmvvmbase.core.base.ui.NavComponent
import com.example.kotlinmvvmbase.databinding.ActivityAuthBinding

class AuthActivity : BaseActivity<ActivityAuthBinding, AuthActivityVM>(ActivityAuthBinding::inflate), NavComponent {

    override val navController: NavController
        get() = findNavController(R.id.fragment2)

    override fun initViewModel(): AuthActivityVM {
        return ViewModelProvider(this).get(AuthActivityVM::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupNavComponent(activity = this)
    }

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