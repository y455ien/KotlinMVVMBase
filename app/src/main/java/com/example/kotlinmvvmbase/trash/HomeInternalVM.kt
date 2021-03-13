package com.example.kotlinmvvmbase.trash

import androidx.lifecycle.ViewModel
import com.example.kotlinmvvmbase.util.BaseCommunicator

class HomeInternalVM(val value: String) : ViewModel() {
    init {
        BaseCommunicator.updateToastStatue(value)
    }
}