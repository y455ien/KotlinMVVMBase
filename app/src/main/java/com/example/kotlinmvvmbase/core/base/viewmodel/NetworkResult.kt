package com.example.kotlinmvvmbase.core.base.viewmodel

import com.example.kotlinmvvmbase.core.network.model.error.APIError

interface NetworkResult<T> {
    fun onSuccess(t: T)

    fun onError(apiError: APIError)

    fun onException(error: Throwable)
}