package com.example.kotlinmvvmbase.ui.home.internal_home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinmvvmbase.core.base.repository.BaseRepository
import com.example.kotlinmvvmbase.core.base.ui.BaseFragment
import com.example.kotlinmvvmbase.core.base.viewmodel.BaseViewModel
import com.example.kotlinmvvmbase.util.GenericViewModelFactory
import com.example.kotlinmvvmbase.databinding.FragmentHomeInternalBinding
import com.example.kotlinmvvmbase.viewmodel.HomeInternalVM

class HomeInternal : BaseFragment<FragmentHomeInternalBinding, HomeInternalVM>(FragmentHomeInternalBinding::inflate) {
    private lateinit var factory: GenericViewModelFactory<HomeInternalVM>

    override fun initViewModel(): HomeInternalVM {
        factory = GenericViewModelFactory({ HomeInternalVM("Home Internal", BaseRepository()) }, HomeInternalVM::class.java)
        return ViewModelProvider(this, factory).get(HomeInternalVM::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button2.setOnClickListener() {
            vm.getData()
        }
        vm.carLiveData.observe(viewLifecycleOwner) {
            it.parts?.data?.first()?.name?.let { it1 -> showToast(it1) }
        }
    }
}