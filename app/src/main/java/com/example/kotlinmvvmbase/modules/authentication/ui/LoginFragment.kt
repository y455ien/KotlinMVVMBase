package com.example.kotlinmvvmbase.modules.authentication.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinmvvmbase.core.base.ui.BaseFragment
import com.example.kotlinmvvmbase.databinding.FragmentLoginBinding
import com.example.kotlinmvvmbase.modules.authentication.viewmodel.LoginFragmentVM
import com.example.kotlinmvvmbase.util.GenericViewModelFactory


class LoginFragment :
        BaseFragment<FragmentLoginBinding, LoginFragmentVM>(FragmentLoginBinding::inflate) {
    private lateinit var factory: GenericViewModelFactory<LoginFragmentVM>

    override fun initViewModel(): LoginFragmentVM {
        factory = GenericViewModelFactory(
                { LoginFragmentVM() },
                LoginFragmentVM::class.java
        )
        return ViewModelProvider(this, factory).get(LoginFragmentVM::class.java)
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