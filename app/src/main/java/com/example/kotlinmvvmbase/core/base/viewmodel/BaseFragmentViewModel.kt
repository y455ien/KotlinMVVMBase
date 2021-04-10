package com.example.kotlinmvvmbase.core.base.viewmodel

import androidx.lifecycle.ViewModel
import com.example.kotlinmvvmbase.core.base.navigation.NavDestinationWrapper
import com.example.kotlinmvvmbase.core.network.model.error.APIError
import com.example.kotlinmvvmbase.core.network.model.error.APIErrorType

abstract class BaseFragmentViewModel : ViewModel() {
    private lateinit var sharedViewModel: BaseActivityViewModel

    protected fun handleError(apiError: APIError) {
        when (apiError.errorType) {
            APIErrorType.UNKNOWN -> showErrorToast(apiError.unknownErrorMessage)
            APIErrorType.UN_AUTHORIZED -> onAuthorizedStatusUpdate(false)
            APIErrorType.SERVER -> apiError.errors?.first()?.message?.let { showErrorToast(it) }
        }
    }

    protected fun handleException(error: Throwable) {

    }

    fun showLoading() {
        sharedViewModel.pushLoadingEvent(true)
    }

    fun hideLoading() {
        sharedViewModel.pushLoadingEvent(false)
    }

    fun showErrorToast(error: String) {
        sharedViewModel.pushErrorEvent(error)
    }

    fun navigateWithAction(destination: NavDestinationWrapper) {
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