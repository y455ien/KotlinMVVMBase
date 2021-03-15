package com.example.kotlinmvvmbase.modules.authentication.ui

import androidx.lifecycle.ViewModelProvider
import com.example.kotlinmvvmbase.core.base.ui.BaseFragment
import com.example.kotlinmvvmbase.databinding.FragmentVerificationBinding
import com.example.kotlinmvvmbase.modules.authentication.viewmodel.VerificationFragmentVM
import com.example.kotlinmvvmbase.util.GenericViewModelFactory


class VerificationFragment : BaseFragment<FragmentVerificationBinding, VerificationFragmentVM>(FragmentVerificationBinding::inflate) {
    private lateinit var factory: GenericViewModelFactory<VerificationFragmentVM>

    override fun initViewModel(): VerificationFragmentVM {
        factory = GenericViewModelFactory(
            { VerificationFragmentVM() },
                VerificationFragmentVM::class.java
        )
        return ViewModelProvider(this, factory).get(VerificationFragmentVM::class.java)
    }
}