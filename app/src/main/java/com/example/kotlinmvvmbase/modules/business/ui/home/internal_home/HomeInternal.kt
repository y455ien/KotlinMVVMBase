package com.example.kotlinmvvmbase.modules.business.ui.home.internal_home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinmvvmbase.modules.business.BusinessActivityVM
import com.example.kotlinmvvmbase.core.base.ui.BaseFragment
import com.example.kotlinmvvmbase.util.GenericViewModelFactory
import com.example.kotlinmvvmbase.databinding.FragmentHomeInternalBinding
import com.example.kotlinmvvmbase.modules.business.repository.CarRepository
import com.example.kotlinmvvmbase.modules.business.viewmodel.HomeInternalVM

class HomeInternal : BaseFragment<FragmentHomeInternalBinding, HomeInternalVM, BusinessActivityVM>(FragmentHomeInternalBinding::inflate, BusinessActivityVM::class.java) {
    private lateinit var factory: GenericViewModelFactory<HomeInternalVM>

    override fun initViewModel(): HomeInternalVM {
        factory = GenericViewModelFactory({ HomeInternalVM("Home Internal", CarRepository()) }, HomeInternalVM::class.java)
        return ViewModelProvider(this, factory).get(HomeInternalVM::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button2.setOnClickListener() {
            vm.getData()
        }
        vm.carLiveData.observe(viewLifecycleOwner) {
            it.parts?.data?.first()?.name?.let { it1 -> vm.showToast(it1) }
        }
    }
}