package com.example.kotlinmvvmbase.core.base.ui

import androidx.lifecycle.MutableLiveData
import com.example.kotlinmvvmbase.core.base.navigation.NavDestinationWrapper

object BaseCommunicator {
    val getIsLoadingEvent: MutableLiveData<SingleLiveEvent<Boolean>> = MutableLiveData()

    val getToastEvent: MutableLiveData<SingleLiveEvent<String>> = MutableLiveData()

    val getErrorEvent: MutableLiveData<SingleLiveEvent<String>> = MutableLiveData()

    val getNavigationEvent = MutableLiveData<SingleLiveEvent<NavDestinationWrapper>>()

    val getUnauthenticatedEvent: MutableLiveData<SingleLiveEvent<Boolean>> = MutableLiveData()

    val getSwapLanguageEvent: MutableLiveData<SingleLiveEvent<Boolean>> = MutableLiveData()

    fun pushLoadingEvent(bool: Boolean) =
        getIsLoadingEvent.postValue(SingleLiveEvent<Boolean>(bool))

    fun pushUnauthenticatedEven(bool: Boolean) =
        getUnauthenticatedEvent.postValue(SingleLiveEvent<Boolean>(bool))

    fun pushToastEvent(value: String) = getToastEvent.postValue(SingleLiveEvent<String>(value))

    fun pushErrorEvent(error: String) = getErrorEvent.postValue(SingleLiveEvent<String>(error))

    fun pushNavigationEvent(action: NavDestinationWrapper) = getNavigationEvent.postValue(
        SingleLiveEvent<NavDestinationWrapper>(action)
    )

    fun pushSwapLanguageEvent(value: Boolean) =
        getSwapLanguageEvent.postValue(SingleLiveEvent<Boolean>(value))
}