package com.example.kotlinmvvmbase

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kotlinmvvmbase.core.base.BaseFragment
import com.example.kotlinmvvmbase.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    override fun initMembers() {

    }

    override fun observeLiveData() {
        navigate()
    }

    override fun getInitialData() {

    }
}