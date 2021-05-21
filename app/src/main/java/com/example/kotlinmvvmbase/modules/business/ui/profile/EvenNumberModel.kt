package com.example.kotlinmvvmbase.modules.business.ui.profile

import androidx.viewbinding.ViewBinding
import com.example.kotlinmvvmbase.core.base.viewmodel.BaseActivityViewModel
import com.example.kotlinmvvmbase.core.base.viewmodel.BaseFragmentViewModel

class EvenNumberModel<VB : ViewBinding>(val value: Int) : ItemViewModel {
    override fun getData(): Int {
        return value
    }
}