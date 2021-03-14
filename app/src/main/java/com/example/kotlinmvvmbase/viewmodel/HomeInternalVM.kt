package com.example.kotlinmvvmbase.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.kotlinmvvmbase.core.base.repository.BaseRepository
import com.example.kotlinmvvmbase.core.base.viewmodel.BaseViewModel
import com.example.kotlinmvvmbase.core.base.viewmodel.NetworkResult
import com.example.kotlinmvvmbase.core.network.RetroCallback
import com.example.kotlinmvvmbase.core.network.model.response.datamodel.error.APIError
import com.example.kotlinmvvmbase.core.network.model.response.datamodel.error.APIErrorType
import com.example.kotlinmvvmbase.core.network.model.response.datamodel.parts.PartsList
import com.example.kotlinmvvmbase.util.BaseCommunicator
import kotlinx.coroutines.launch

class HomeInternalVM(val value: String, private val repository: BaseRepository) : BaseViewModel() {
    val carLiveData: MutableLiveData<PartsList> = MutableLiveData()

    init {
        Log.e("YASSIEN", "$value: View Model Created")
    }

    fun getData() {
        viewModelScope.launch {
            showLoading()
            repository.getData(RetroCallback(object : NetworkResult<PartsList> {
                override fun onSuccess(t: PartsList) {
                    hideLoading()
                    carLiveData.postValue(t)
                }

                override fun onError(apiError: APIError) {
                    hideLoading()
                    handleError(apiError)
                }

                override fun onException(error: Throwable) {
                    hideLoading()
                    handleException(error)
                }
            }))
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.e("YASSIEN", "$value: View Model Destroyed")
    }
}