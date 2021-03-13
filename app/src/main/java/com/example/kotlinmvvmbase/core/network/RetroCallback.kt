package com.example.kotlinmvvmbase.core.network

import com.example.kotlinmvvmbase.core.base.viewmodel.ViewModelCallback
import retrofit2.Call
import retrofit2.Response

class RetroCallback<T>(private val ViewModelCallback: ViewModelCallback<T>) : retrofit2.Callback<WebResponse<T>> {
    override fun onResponse(call: Call<WebResponse<T>>, response: Response<WebResponse<T>>) {
        when {
            (response.isSuccessful && response.body()?.data != null) -> ViewModelCallback.onSuccess(response.body()?.data!!)
            (response.body()?.error != null) -> ViewModelCallback.onError(response.body()?.error!!)
        }
    }

    override fun onFailure(call: Call<WebResponse<T>>, t: Throwable) {
        ViewModelCallback.onException(t)
    }
}