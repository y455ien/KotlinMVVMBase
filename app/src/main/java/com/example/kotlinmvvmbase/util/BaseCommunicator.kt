package com.example.kotlinmvvmbase.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

object BaseCommunicator {
    private val isLoadingLiveData = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = isLoadingLiveData

    private val toastLiveData: MutableLiveData<String> = MutableLiveData()
    val toast: LiveData<String> = toastLiveData

    private val navigationLiveData = MutableLiveData<Int>()
    val navigation: LiveData<Int> = navigationLiveData

    fun updateLoadingStatus(bool: Boolean) = isLoadingLiveData.postValue(bool)

    fun updateToastStatue(string: String) = toastLiveData.postValue(string)

    fun navigate(actionId: Int) = navigationLiveData.postValue(actionId)
}