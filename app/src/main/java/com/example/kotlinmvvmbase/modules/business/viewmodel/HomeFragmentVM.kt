package com.example.kotlinmvvmbase.modules.business.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.kotlinmvvmbase.core.base.viewmodel.BaseViewModel
import com.example.kotlinmvvmbase.modules.business.repository.CarRepository
import kotlinx.coroutines.launch

class HomeFragmentVM : BaseViewModel() {
    private val repo = CarRepository()

    fun swapLanguage() {
        repo.changeLanguage()
    }
}