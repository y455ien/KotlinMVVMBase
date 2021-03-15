package com.example.kotlinmvvmbase.modules.business.ui.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinmvvmbase.core.base.ui.BaseFragment
import com.example.kotlinmvvmbase.databinding.FragmentHomeBinding
import com.example.kotlinmvvmbase.modules.business.viewmodel.HomeFragmentVM
import com.example.kotlinmvvmbase.util.GenericViewModelFactory

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeFragmentVM>(FragmentHomeBinding::inflate) {
    private lateinit var factory: GenericViewModelFactory<HomeFragmentVM>

    override fun initViewModel(): HomeFragmentVM {
        factory = GenericViewModelFactory({ HomeFragmentVM() }, HomeFragmentVM::class.java)
        return ViewModelProvider(this, factory).get(HomeFragmentVM::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button.setOnClickListener() {
            val action = HomeFragmentDirections.actionHomeFragmentToHomeInternal()
            navigateWithAction(action)
        }
    }

}