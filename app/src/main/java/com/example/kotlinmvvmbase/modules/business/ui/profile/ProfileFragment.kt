package com.example.kotlinmvvmbase.modules.business.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinmvvmbase.R
import com.example.kotlinmvvmbase.core.base.ui.BaseFragment
import com.example.kotlinmvvmbase.databinding.FragmentProfileBinding
import com.example.kotlinmvvmbase.modules.business.viewmodel.HomeFragmentVM
import com.example.kotlinmvvmbase.modules.business.viewmodel.ProfileFragmentVM
import com.example.kotlinmvvmbase.util.GenericViewModelFactory

class ProfileFragment : BaseFragment<FragmentProfileBinding, ProfileFragmentVM>(FragmentProfileBinding::inflate) {
    private lateinit var factory: GenericViewModelFactory<ProfileFragmentVM>

    override fun initViewModel(): ProfileFragmentVM {
        factory = GenericViewModelFactory({ ProfileFragmentVM() }, ProfileFragmentVM::class.java)
        return ViewModelProvider(this, factory).get(ProfileFragmentVM::class.java)
    }
}