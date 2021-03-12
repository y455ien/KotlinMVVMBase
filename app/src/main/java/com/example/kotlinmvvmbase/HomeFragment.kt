package com.example.kotlinmvvmbase

import android.widget.Toast
import com.example.kotlinmvvmbase.core.base.BaseFragment
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