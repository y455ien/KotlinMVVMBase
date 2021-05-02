package com.example.kotlinmvvmbase.core.base.viewmodel

import com.example.kotlinmvvmbase.constant.Constant
import kotlinx.coroutines.CoroutineExceptionHandler
import retrofit2.HttpException
import java.net.SocketTimeoutException
import kotlin.coroutines.AbstractCoroutineContextElement
import kotlin.coroutines.CoroutineContext

class ExceptionHandler(private val viewModel: BaseFragmentViewModel) : AbstractCoroutineContextElement(CoroutineExceptionHandler), CoroutineExceptionHandler {

    override fun handleException(context: CoroutineContext, exception: Throwable) {
        when (exception) {
            is SocketTimeoutException -> handleTimeoutException(exception)
            is HttpException -> handleHTTPException(exception)
            else -> handleUnknownException(exception)
        }
    }

    private fun handleTimeoutException(exception: SocketTimeoutException) {

    }

    private fun handleHTTPException(exception: HttpException) {
        when (exception.code()) {
            HTTPEnums.UN_PROCESSABLE_ENTITY.value() -> handleUnProcessableEntity(exception)
            HTTPEnums.UN_AUTHORIZED.value() -> handleUnauthorizedStatus(exception)
        }
    }

    private fun handleUnProcessableEntity(exception: HttpException) {
        val rawErrorResponse = exception.response()?.errorBody()?.string()
        rawErrorResponse?.let {
            viewModel.showErrorToast(it)
        }
    }

    private fun handleUnauthorizedStatus(exception: HttpException) {
        val rawErrorResponse = exception.response()?.errorBody()?.string()
        rawErrorResponse?.let {
            viewModel.showErrorToast(it)
        }
    }

    private fun handleUnknownException(exception: Throwable) {
        viewModel.showErrorToast(Constant.Error.NETWORK_ERROR_MSG)
        exception.printStackTrace()
    }

    private enum class HTTPEnums {
        UN_PROCESSABLE_ENTITY,
        UN_AUTHORIZED
    }

    private fun HTTPEnums.value(): Int {
        return when (this) {
            HTTPEnums.UN_PROCESSABLE_ENTITY -> 422
            HTTPEnums.UN_AUTHORIZED -> 401
        }
    }
}