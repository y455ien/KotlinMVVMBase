package com.example.kotlinmvvmbase

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinmvvmbase.core.base.repository.BaseRepository
import com.example.kotlinmvvmbase.core.base.ui.BaseFragment
import com.example.kotlinmvvmbase.databinding.FragmentSignupBinding
import com.example.kotlinmvvmbase.util.BaseCommunicator
import com.example.kotlinmvvmbase.util.GenericViewModelFactory
import com.example.kotlinmvvmbase.viewmodel.HomeInternalVM


class SignupFragment : BaseFragment<FragmentSignupBinding, HomeInternalVM>(FragmentSignupBinding::inflate) {
    private lateinit var factory: GenericViewModelFactory<HomeInternalVM>

    override fun initViewModel(): HomeInternalVM {
        factory = GenericViewModelFactory(
            { HomeInternalVM("Home Internal", BaseRepository()) },
            HomeInternalVM::class.java
        )
        return ViewModelProvider(this, factory).get(HomeInternalVM::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.nextButton.setOnClickListener() {
            val action = SignupFragmentDirections.actionSignupFragmentToVerificationFragment()
            navigateWithAction(action)
        }
    }

}