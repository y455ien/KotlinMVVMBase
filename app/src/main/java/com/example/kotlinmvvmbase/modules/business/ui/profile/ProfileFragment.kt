package com.example.kotlinmvvmbase.modules.business.ui.profile

import androidx.lifecycle.ViewModelProvider
import com.example.kotlinmvvmbase.modules.business.BusinessActivityVM
import com.example.kotlinmvvmbase.core.base.ui.BaseFragment
import com.example.kotlinmvvmbase.databinding.FragmentProfileBinding
import com.example.kotlinmvvmbase.modules.business.viewmodel.ProfileFragmentVM
import com.example.kotlinmvvmbase.util.GenericViewModelFactory

class ProfileFragment : BaseFragment<FragmentProfileBinding, ProfileFragmentVM, BusinessActivityVM>(FragmentProfileBinding::inflate, BusinessActivityVM::class.java) {
    private lateinit var factory: GenericViewModelFactory<ProfileFragmentVM>

    override fun initViewModel(): ProfileFragmentVM {
        factory = GenericViewModelFactory({ ProfileFragmentVM() }, ProfileFragmentVM::class.java)
        return ViewModelProvider(this, factory).get(ProfileFragmentVM::class.java)
    }
}