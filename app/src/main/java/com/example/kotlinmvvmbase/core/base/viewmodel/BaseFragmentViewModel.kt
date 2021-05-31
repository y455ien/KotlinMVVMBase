package com.example.kotlinmvvmbase.core.base.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinmvvmbase.core.base.navigation.Destination
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.plus

abstract class BaseFragmentViewModel(_scope: CoroutineScope? = null) : ViewModel() {
    protected val scope: CoroutineScope by lazy { _scope?: viewModelScope.plus(ExceptionHandler(this)) }
    private lateinit var sharedViewModel: BaseActivityViewModel

    fun showLoading() {
        sharedViewModel.pushLoadingEvent(true)
    }

    fun hideLoading() {
        sharedViewModel.pushLoadingEvent(false)
    }

    fun showErrorToast(error: String) {
        sharedViewModel.pushErrorEvent(error)
    }

    fun navigateWithAction(destination: Destination) {
        sharedViewModel.pushNavigationEvent(destination)
    }

    fun showToast(message: String) {
        sharedViewModel.pushToastEvent(message)
    }

    fun swapLanguage() {
        sharedViewModel.pushSwapLanguageEvent(true)
    }

    fun bind(activityViewModel: BaseActivityViewModel): BaseFragmentViewModel {
        sharedViewModel = activityViewModel
        return this
    }

    private fun onAuthorizedStatusUpdate(authorizationStatus: Boolean) {
        sharedViewModel.pushUnauthenticatedEven(authorizationStatus)
    }
}