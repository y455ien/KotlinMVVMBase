package com.example.kotlinmvvmbase.modules.business.ui.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinmvvmbase.R
import com.example.kotlinmvvmbase.core.base.ui.BaseFragment
import com.example.kotlinmvvmbase.databinding.FragmentSettingsBinding
import com.example.kotlinmvvmbase.modules.business.viewmodel.ProfileFragmentVM
import com.example.kotlinmvvmbase.modules.business.viewmodel.SettingsFragmentVM
import com.example.kotlinmvvmbase.util.GenericViewModelFactory

class SettingsFragment : BaseFragment<FragmentSettingsBinding, SettingsFragmentVM>(FragmentSettingsBinding::inflate) {
    private lateinit var factory: GenericViewModelFactory<SettingsFragmentVM>

    override fun initViewModel(): SettingsFragmentVM {
        factory = GenericViewModelFactory({ SettingsFragmentVM() }, SettingsFragmentVM::class.java)
        return ViewModelProvider(this, factory).get(SettingsFragmentVM::class.java)
    }
}