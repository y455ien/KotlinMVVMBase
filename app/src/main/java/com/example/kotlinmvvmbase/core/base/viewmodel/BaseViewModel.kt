package com.example.kotlinmvvmbase.core.base.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlinmvvmbase.core.network.model.response.datamodel.error.APIError
import com.example.kotlinmvvmbase.core.network.model.response.datamodel.error.APIErrorType
import com.example.kotlinmvvmbase.util.BaseCommunicator

abstract class BaseViewModel : ViewModel() {
    private val _toastLiveData: MutableLiveData<String> = MutableLiveData()
    val toast: LiveData<String> = _toastLiveData

    private val _authorizationStatusLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val authorizationStatus: LiveData<Boolean> = _authorizationStatusLiveData

    private val _loadingStatusLiveData = MutableLiveData<Boolean>()
    val loadingStatus: LiveData<Boolean> = _loadingStatusLiveData

    protected fun handleError(apiError: APIError) {
        when (apiError.errorType) {
            APIErrorType.UNKNOWN -> showToast("Something went wrong")
            APIErrorType.UN_AUTHORIZED -> _authorizationStatusLiveData.postValue(false)
            APIErrorType.SERVER -> apiError.errors?.first()?.message?.let { showToast(it) }
        }
    }

    protected fun handleException(error: Throwable) {

    }

    protected fun showLoading() {
        _loadingStatusLiveData.postValue(true)
    }

    protected fun hideLoading() {
        _loadingStatusLiveData.postValue(false)
    }

    protected fun showToast(message: String) {
        _toastLiveData.postValue(message)
    }
}