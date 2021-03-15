package com.example.kotlinmvvmbase.modules.authentication.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinmvvmbase.R
import com.example.kotlinmvvmbase.core.base.navigation.NavDestinationWrapper
import com.example.kotlinmvvmbase.core.base.ui.BaseFragment
import com.example.kotlinmvvmbase.databinding.FragmentSignupBinding
import com.example.kotlinmvvmbase.modules.authentication.viewmodel.SignupFragmentVM
import com.example.kotlinmvvmbase.util.GenericViewModelFactory


class SignupFragment : BaseFragment<FragmentSignupBinding, SignupFragmentVM>(FragmentSignupBinding::inflate) {
    private lateinit var factory: GenericViewModelFactory<SignupFragmentVM>

    override fun initViewModel(): SignupFragmentVM {
        factory = GenericViewModelFactory(
                { SignupFragmentVM() },
                SignupFragmentVM::class.java
        )
        return ViewModelProvider(this, factory).get(SignupFragmentVM::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.nextButton.setOnClickListener() {
            val action = SignupFragmentDirections.actionSignupFragmentToVerificationFragment()
            navigateWithAction(NavDestinationWrapper(action, popUpTo = R.id.loginFragment))
        }
    }

}