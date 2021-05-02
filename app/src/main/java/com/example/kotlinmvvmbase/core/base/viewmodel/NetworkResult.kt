package com.example.kotlinmvvmbase.core.base.viewmodel

import com.example.kotlinmvvmbase.core.deprecated.network_deprecated.model.error.APIError

interface NetworkResult<T> {
    fun onSuccess(t: T)

    fun onError(apiError: APIError)

    fun onException(error: Throwable)
}