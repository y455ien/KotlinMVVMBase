package com.example.kotlinmvvmbase.ui

import androidx.lifecycle.ViewModelProvider
import com.example.kotlinmvvmbase.core.base.BaseFragment
import com.example.kotlinmvvmbase.util.MyViewModelFactory
import com.example.kotlinmvvmbase.databinding.FragmentHomeInternalBinding
import com.example.kotlinmvvmbase.trash.HomeInternalVM

class HomeInternal : BaseFragment<FragmentHomeInternalBinding>(FragmentHomeInternalBinding::inflate) {
    private lateinit var factory: MyViewModelFactory<HomeInternalVM>
    private lateinit var vm: HomeInternalVM

    override fun initMembers() {
        factory = MyViewModelFactory({ HomeInternalVM("Hello") }, HomeInternalVM::class.java)
        vm = ViewModelProvider(this, factory).get(HomeInternalVM::class.java)
    }

    override fun observeLiveData() {
    }

    override fun getInitialData() {
        binding.button2.setOnClickListener {
            showLoading()
        }
    }
}