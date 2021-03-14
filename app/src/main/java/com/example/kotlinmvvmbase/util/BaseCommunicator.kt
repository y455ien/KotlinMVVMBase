package com.example.kotlinmvvmbase.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

object BaseCommunicator {
    val isLoading = MutableLiveData<Boolean>()

    val toast: MutableLiveData<String> = MutableLiveData()

    val navigation = MutableLiveData<Int>()

    val authorizationStatus: MutableLiveData<Boolean> = MutableLiveData()

    fun updateLoadingStatus(bool: Boolean) = isLoading.postValue(bool)

    fun updateAuthorizationStatus(bool: Boolean) = authorizationStatus.postValue(bool)

    fun updateToastStatue(string: String) = toast.postValue(string)

    fun navigate(actionId: Int) = navigation.postValue(actionId)
}