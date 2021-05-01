package com.example.kotlinmvvmbase.core.base.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinmvvmbase.core.base.navigation.NavDestinationWrapper
import com.example.kotlinmvvmbase.core.base.repository.BaseRepository
import com.example.kotlinmvvmbase.core.network.model.error.APIError
import com.example.kotlinmvvmbase.core.network.model.error.APIErrorType
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.plus
import kotlin.coroutines.CoroutineContext

abstract class BaseFragmentViewModel(_scope: CoroutineScope? = null) : ViewModel() {
    protected val scope: CoroutineScope by lazy { _scope?: viewModelScope.plus(ExceptionHandler(this)) }
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