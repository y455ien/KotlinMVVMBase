package com.example.kotlinmvvmbase.ui.home.internal_home

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinmvvmbase.core.base.repository.BaseRepository
import com.example.kotlinmvvmbase.core.base.ui.BaseFragment
import com.example.kotlinmvvmbase.util.GenericViewModelFactory
import com.example.kotlinmvvmbase.databinding.FragmentHomeInternalBinding
import com.example.kotlinmvvmbase.viewmodel.HomeInternalVM

class HomeInternal : BaseFragment<FragmentHomeInternalBinding>(FragmentHomeInternalBinding::inflate) {
    private lateinit var factory: GenericViewModelFactory<HomeInternalVM>
    private lateinit var vm: HomeInternalVM

    override fun initMembers() {
        factory = GenericViewModelFactory({ HomeInternalVM("Passed Argument", BaseRepository()) }, HomeInternalVM::class.java)
        vm = ViewModelProvider(this, factory).get(HomeInternalVM::class.java)
    }

    override fun observeLiveData() {
        vm.carLiveData.observe(this, Observer {
            it.parts?.data?.first()?.id?.let { it1 -> showToast(it1) }
            hideLoading()
        })
    }

    override fun getInitialData() {
        binding.button2.setOnClickListener {
            showLoading()
            vm.getData()
        }
    }
}