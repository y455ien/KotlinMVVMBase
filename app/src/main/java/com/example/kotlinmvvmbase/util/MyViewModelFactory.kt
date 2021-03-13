package com.example.kotlinmvvmbase.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class MyViewModelFactory<VM : ViewModel>(private val constructorInvoker: () -> VM, private val vmClass: Class<VM>) : ViewModelProvider.Factory {

    override fun <VM : ViewModel?> create(modelClass: Class<VM>): VM {
        if (modelClass.isAssignableFrom(vmClass)) {
            @Suppress("UNCHECKED_CAST")
            return (constructorInvoker()) as VM
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}