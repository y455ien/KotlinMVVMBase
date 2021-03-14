package com.example.kotlinmvvmbase.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDirections

object BaseCommunicator {
    val isLoading = MutableLiveData<Boolean>()

    val toast: MutableLiveData<String> = MutableLiveData()

    val errorToast: MutableLiveData<String> = MutableLiveData()

    val navigationWithAction = MutableLiveData<NavDirections>()

    val authorizationStatus: MutableLiveData<Boolean> = MutableLiveData()

    fun updateLoadingStatus(bool: Boolean) = isLoading.postValue(bool)

    fun updateAuthorizationStatus(bool: Boolean) = authorizationStatus.postValue(bool)

    fun updateToast(string: String) = toast.postValue(string)

    fun updateErrorToast(string: String) = errorToast.postValue(string)

    fun navigateWithAction(action: NavDirections) = navigationWithAction.postValue(action)
}