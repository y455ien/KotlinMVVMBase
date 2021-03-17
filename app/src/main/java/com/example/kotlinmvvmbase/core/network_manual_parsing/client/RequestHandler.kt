package com.example.kotlinmvvmbase.core.network_manual_parsing.client

import com.example.kotlinmvvmbase.core.base.viewmodel.NetworkResult
import com.example.kotlinmvvmbase.core.network.model.error.APIError
import com.example.kotlinmvvmbase.core.network.model.error.APIErrorType
import com.google.gson.Gson
import com.google.gson.JsonElement
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response

class RequestHandler<T>(private val modelClass: Class<T>, private val networkResult: NetworkResult<T>) : retrofit2.Callback<JsonElement> {
    override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
        if (response.isSuccessful) {
            networkResult.onSuccess(convertBodyToModel(response.body()))
        } else {
            networkResult.onError(convertBodyToError(response.code(), response.errorBody()))
        }
    }

    override fun onFailure(call: Call<JsonElement>, t: Throwable) {
        networkResult.onException(t)
    }

    private fun convertBodyToModel(data: JsonElement?): T {
        val response = data?.asJsonObject
        return Gson().fromJson(response?.get("data")?: response, modelClass)
    }

    private fun convertBodyToError(code: Int, data: ResponseBody?): APIError {
        data?.let {
            val apiError: APIError = Gson().fromJson(data.charStream(), APIError::class.java)
            apiError.code = code
            apiError.errorType = if (code == 401) APIErrorType.UN_AUTHORIZED else APIErrorType.SERVER
            return apiError
        }
        return APIError(
                code = code,
                errorType = APIErrorType.UNKNOWN
        )
    }
}