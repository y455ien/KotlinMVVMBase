package com.example.kotlinmvvmbase

import androidx.lifecycle.ViewModelProvider
import com.example.kotlinmvvmbase.core.base.repository.BaseRepository
import com.example.kotlinmvvmbase.core.base.ui.BaseFragment
import com.example.kotlinmvvmbase.databinding.FragmentVerificationBinding
import com.example.kotlinmvvmbase.util.GenericViewModelFactory
import com.example.kotlinmvvmbase.viewmodel.HomeInternalVM


class VerificationFragment : BaseFragment<FragmentVerificationBinding, HomeInternalVM>(FragmentVerificationBinding::inflate) {
    private lateinit var factory: GenericViewModelFactory<HomeInternalVM>

    override fun initViewModel(): HomeInternalVM {
        factory = GenericViewModelFactory(
            { HomeInternalVM("Home Internal", BaseRepository()) },
            HomeInternalVM::class.java
        )
        return ViewModelProvider(this, factory).get(HomeInternalVM::class.java)
    }
}