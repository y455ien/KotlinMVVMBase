package com.example.kotlinmvvmbase.core.network.model.error

import com.example.kotlinmvvmbase.constant.Constant

data class APIError(
        var code: Int,
        var errorType: APIErrorType,
        val errors: List<Error>? = null,
        val unknownErrorMessage: String = Constant.Error.NETWORK_ERROR_MSG,
)

enum class APIErrorType {
    SERVER,
    UN_AUTHORIZED,
    UNKNOWN
}