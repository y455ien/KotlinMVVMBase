package com.example.kotlinmvvmbase.core.base.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlinmvvmbase.core.network.model.error.APIError
import com.example.kotlinmvvmbase.core.network.model.error.APIErrorType

abstract class BaseViewModel : ViewModel() {
    private val _errorToastLiveData: MutableLiveData<String> = MutableLiveData()
    val errorToast: LiveData<String> = _errorToastLiveData

    private val _authorizationStatusLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val authorizationStatus: LiveData<Boolean> = _authorizationStatusLiveData

    private val _loadingStatusLiveData = MutableLiveData<Boolean>()
    val loadingStatus: LiveData<Boolean> = _loadingStatusLiveData

    protected fun handleError(apiError: APIError) {
        when (apiError.errorType) {
            APIErrorType.UNKNOWN -> showErrorToast(apiError.unknownErrorMessage)
            APIErrorType.UN_AUTHORIZED -> _authorizationStatusLiveData.postValue(false)
            APIErrorType.SERVER -> apiError.errors?.first()?.message?.let { showErrorToast(it) }
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

    protected fun showErrorToast(error: String) {
        _errorToastLiveData.postValue(error)
    }
}