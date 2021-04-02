package com.example.kotlinmvvmbase.modules.splash

import android.os.Bundle
import android.os.LocaleList
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.kotlinmvvmbase.R
import com.example.kotlinmvvmbase.core.base.navigation.NavDestinationWrapper
import com.example.kotlinmvvmbase.core.base.ui.BaseFragment
import com.example.kotlinmvvmbase.databinding.FragmentSplashBinding
import com.example.kotlinmvvmbase.modules.business.repository.CarRepository
import com.example.kotlinmvvmbase.util.GenericViewModelFactory
import com.example.kotlinmvvmbase.modules.business.viewmodel.HomeInternalVM
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashFragment :
        BaseFragment<FragmentSplashBinding, HomeInternalVM>(FragmentSplashBinding::inflate) {
    private lateinit var factory: GenericViewModelFactory<HomeInternalVM>

    override fun initViewModel(): HomeInternalVM {
        factory = GenericViewModelFactory(
                { HomeInternalVM("Home Internal", CarRepository()) },
                HomeInternalVM::class.java
        )
        return ViewModelProvider(this, factory).get(HomeInternalVM::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            delay(3000)
            val action = SplashFragmentDirections.actionSplashFragmentToHomeFragment()
            navigateWithAction(NavDestinationWrapper(action, popUpTo = R.id.main_nav_graph, isInclusive = true))
        }
    }
}