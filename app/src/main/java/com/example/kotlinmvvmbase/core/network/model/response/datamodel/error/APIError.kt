package com.example.kotlinmvvmbase.core.network.model.response.datamodel.error

data class APIError(
        var code: Int,
        var errorType: APIErrorType,
        val errors: List<Error>? = null,
        val unknownErrorMessage: String = "Something went wrong",
)

enum class APIErrorType {
    SERVER,
    UN_AUTHORIZED,
    UNKNOWN
}