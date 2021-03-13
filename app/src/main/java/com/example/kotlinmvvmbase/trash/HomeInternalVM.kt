package com.example.kotlinmvvmbase.trash

import android.util.Log
import com.example.kotlinmvvmbase.core.base.BaseViewModel
import com.example.kotlinmvvmbase.util.BaseCommunicator

class HomeInternalVM(val value: String) : BaseViewModel() {
    init {
        BaseCommunicator.updateToastStatue(value)
        Log.e("YASSIEN", "VM Created")
    }

    override fun onCleared() {
        super.onCleared()
        Log.e("YASSIEN", "VM Cleared")
    }
}