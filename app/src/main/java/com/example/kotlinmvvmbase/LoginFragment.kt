package com.example.kotlinmvvmbase

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import com.example.kotlinmvvmbase.core.base.repository.BaseRepository
import com.example.kotlinmvvmbase.core.base.ui.BaseFragment
import com.example.kotlinmvvmbase.databinding.FragmentLoginBinding
import com.example.kotlinmvvmbase.util.BaseCommunicator
import com.example.kotlinmvvmbase.util.GenericViewModelFactory
import com.example.kotlinmvvmbase.viewmodel.HomeInternalVM


class LoginFragment :
    BaseFragment<FragmentLoginBinding, HomeInternalVM>(FragmentLoginBinding::inflate) {
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
        binding.loginButton.setOnClickListener() {
            var userInput = binding.textInputLayout.editText?.text.toString()
            if (userInput.contentEquals("1234")) {
                val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment()
                navigateWithAction(action)
            } else {
                showToast("Wrong input")
            }
        }
        binding.signUpButton.setOnClickListener() {
            val action = LoginFragmentDirections.actionLoginFragmentToSignupFragment()
            navigateWithAction(action)
        }
    }

}