package com.example.kotlinmvvmbase.modules.authentication.sign_in

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.kotlinmvvmbase.core.base.ui.BaseFragment
import com.example.kotlinmvvmbase.databinding.FragmentSignInBinding
import com.example.kotlinmvvmbase.modules.authentication.AuthActivityVM
import com.example.kotlinmvvmbase.modules.business.repository.CarRepository
import com.example.kotlinmvvmbase.modules.business.viewmodel.HomeInternalVM
import com.example.kotlinmvvmbase.util.GenericViewModelFactory

class SignInFragment : BaseFragment<FragmentSignInBinding, HomeInternalVM, AuthActivityVM>(FragmentSignInBinding::inflate, AuthActivityVM::class.java) {
    private lateinit var factory: GenericViewModelFactory<HomeInternalVM>

    override fun initViewModel(): HomeInternalVM {
        factory = GenericViewModelFactory(
                { HomeInternalVM("Home Internal", CarRepository()) },
                HomeInternalVM::class.java
        )
        return ViewModelProvider(this, factory).get(HomeInternalVM::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenStarted {
            vm.getData()
            vm.carLiveData.observe(viewLifecycleOwner, Observer {
                vm.showToast(it.toString())
            })
        }
    }
}