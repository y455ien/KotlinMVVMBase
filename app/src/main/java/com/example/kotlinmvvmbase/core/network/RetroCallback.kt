package com.example.kotlinmvvmbase.core.network

import com.example.kotlinmvvmbase.core.base.viewmodel.NetworkResult
import com.example.kotlinmvvmbase.core.network.model.response.datamodel.error.APIErrorType
import com.example.kotlinmvvmbase.core.network.model.response.datamodel.error.APIError
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response

class RetroCallback<T>(private val NetworkResult: NetworkResult<T>) : retrofit2.Callback<WebResponse<T>> {
    override fun onResponse(call: Call<WebResponse<T>>, response: Response<WebResponse<T>>) {
        if (response.isSuccessful) {
            response.body()?.let {
                NetworkResult.onSuccess(it.data!!)
            }
        } else {
            NetworkResult.onError(convertBodyToError(response.code(), response.errorBody()))
        }
    }

    override fun onFailure(call: Call<WebResponse<T>>, t: Throwable) {
        NetworkResult.onException(t)
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