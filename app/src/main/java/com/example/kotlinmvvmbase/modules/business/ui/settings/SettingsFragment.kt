package com.example.kotlinmvvmbase.modules.business.ui.settings

import androidx.lifecycle.ViewModelProvider
import com.example.kotlinmvvmbase.modules.business.BusinessActivityVM
import com.example.kotlinmvvmbase.core.base.ui.BaseFragment
import com.example.kotlinmvvmbase.databinding.FragmentSettingsBinding
import com.example.kotlinmvvmbase.modules.business.viewmodel.SettingsFragmentVM
import com.example.kotlinmvvmbase.util.GenericViewModelFactory

class SettingsFragment : BaseFragment<FragmentSettingsBinding, SettingsFragmentVM, BusinessActivityVM>(FragmentSettingsBinding::inflate, BusinessActivityVM::class.java) {
    private lateinit var factory: GenericViewModelFactory<SettingsFragmentVM>

    override fun initViewModel(): SettingsFragmentVM {
        factory = GenericViewModelFactory({ SettingsFragmentVM() }, SettingsFragmentVM::class.java)
        return ViewModelProvider(this, factory).get(SettingsFragmentVM::class.java)
    }
}