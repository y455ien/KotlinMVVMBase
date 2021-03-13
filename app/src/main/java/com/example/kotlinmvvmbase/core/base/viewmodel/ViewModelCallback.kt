package com.example.kotlinmvvmbase.core.base.viewmodel

import com.example.kotlinmvvmbase.core.network.model.response.datamodel.error.ErrorList

interface ViewModelCallback<T> {
    fun onException(error: Throwable)

    fun onError(error: ErrorList)

    fun onSuccess(t: T)
}