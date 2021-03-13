package com.example.kotlinmvvmbase.ui.home

import com.example.kotlinmvvmbase.R
import com.example.kotlinmvvmbase.core.base.ui.BaseFragment
import com.example.kotlinmvvmbase.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    override fun initMembers() {

    }

    override fun observeLiveData() {

    }

    override fun getInitialData() {
        binding.button.setOnClickListener {
            navigate(R.id.action_homeFragment_to_homeInternal)
        }
    }
}