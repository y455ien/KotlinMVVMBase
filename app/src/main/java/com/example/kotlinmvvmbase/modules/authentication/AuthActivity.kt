package com.example.kotlinmvvmbase.modules.authentication

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.kotlinmvvmbase.R
import com.example.kotlinmvvmbase.core.base.navigation.Destination
import com.example.kotlinmvvmbase.core.base.ui.BaseActivity
import com.example.kotlinmvvmbase.core.base.navigation.NavigationComponent
import com.example.kotlinmvvmbase.databinding.ActivityAuthBinding

class AuthActivity : BaseActivity<ActivityAuthBinding, AuthActivityVM>(ActivityAuthBinding::inflate), NavigationComponent {

    override val navController: NavController
        get() = findNavController(R.id.fragment2)

    override fun initViewModel(): AuthActivityVM {
        return ViewModelProvider(this).get(AuthActivityVM::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupNavComponent(activity = this)
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