package com.example.kotlinmvvmbase.core.base.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlinmvvmbase.core.base.navigation.Destination

abstract class BaseActivityViewModel: ViewModel() {
    val getIsLoadingEvent: MutableLiveData<SingleLiveEvent<Boolean>> = MutableLiveData()

    val getToastEvent: MutableLiveData<SingleLiveEvent<String>> = MutableLiveData()

    val getErrorEvent: MutableLiveData<SingleLiveEvent<String>> = MutableLiveData()

    val getNavigationEvent = MutableLiveData<SingleLiveEvent<Destination>>()

    val getUnauthenticatedEvent: MutableLiveData<SingleLiveEvent<Boolean>> = MutableLiveData()

    val getSwapLanguageEvent: MutableLiveData<SingleLiveEvent<Boolean>> = MutableLiveData()

    fun pushLoadingEvent(bool: Boolean) =
        getIsLoadingEvent.postValue(SingleLiveEvent<Boolean>(bool))

    fun pushUnauthenticatedEven(bool: Boolean) =
        getUnauthenticatedEvent.postValue(SingleLiveEvent<Boolean>(bool))

    fun pushToastEvent(value: String) = getToastEvent.postValue(SingleLiveEvent<String>(value))

    fun pushErrorEvent(error: String) = getErrorEvent.postValue(SingleLiveEvent<String>(error))

    fun pushNavigationEvent(action: Destination) = getNavigationEvent.postValue(
        SingleLiveEvent(action)
    )

    fun pushSwapLanguageEvent(value: Boolean) =
        getSwapLanguageEvent.postValue(SingleLiveEvent<Boolean>(value))
}