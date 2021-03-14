package com.example.kotlinmvvmbase.ui.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinmvvmbase.R
import com.example.kotlinmvvmbase.core.base.repository.BaseRepository
import com.example.kotlinmvvmbase.core.base.ui.BaseFragment
import com.example.kotlinmvvmbase.databinding.FragmentHomeBinding
import com.example.kotlinmvvmbase.util.GenericViewModelFactory
import com.example.kotlinmvvmbase.viewmodel.HomeInternalVM

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeInternalVM>(FragmentHomeBinding::inflate) {
    private lateinit var factory: GenericViewModelFactory<HomeInternalVM>

    override fun initViewModel(): HomeInternalVM {
        factory = GenericViewModelFactory({ HomeInternalVM("Home", BaseRepository()) }, HomeInternalVM::class.java)
        return ViewModelProvider(this, factory).get(HomeInternalVM::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button.setOnClickListener() {
            navigate(R.id.homeInternal)
        }
    }

}