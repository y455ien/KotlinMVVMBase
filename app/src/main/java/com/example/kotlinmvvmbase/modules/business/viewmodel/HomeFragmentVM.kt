package com.example.kotlinmvvmbase.modules.business.viewmodel

import com.example.kotlinmvvmbase.core.base.viewmodel.BaseFragmentViewModel
import com.example.kotlinmvvmbase.modules.business.repository.CarRepository

class HomeFragmentVM : BaseFragmentViewModel() {
    private val repo = CarRepository()
}