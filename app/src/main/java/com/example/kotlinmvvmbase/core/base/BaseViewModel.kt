package com.example.kotlinmvvmbase.core.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlinmvvmbase.util.BaseCommunicator
import retrofit2.Response

abstract class BaseViewModel : ViewModel() {
    private val informationalStatusCode = 100..199
    private val successfulStatusCode = 200..299
    private val reDirectionalStatusCode = 300..399
    private val clientErrorStatusCode = 400..499
    private val serverErrorStatusCode = 500..599

    private fun <T : Any> handleResponse(response: Response<T>, liveData: MutableLiveData<T>) {
        if (response.isSuccessful)
            response.body()?.let { responseBody ->
                liveData.postValue(responseBody)
            }
        else handleError(response)
    }

    private fun <T : Any> handleError(response: Response<T>) {
        when (response.code()) {
            in informationalStatusCode -> print("Yassien")
            in successfulStatusCode -> print("Yassien")
            in reDirectionalStatusCode -> print("Yassien")
            in clientErrorStatusCode -> print("Yassien")
            in serverErrorStatusCode -> print("Yassien")
            else -> BaseCommunicator.updateToastStatue("Something went wrong")
        }
    }
}