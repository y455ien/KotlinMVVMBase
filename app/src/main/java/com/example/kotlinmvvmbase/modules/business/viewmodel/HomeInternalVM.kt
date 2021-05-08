package com.example.kotlinmvvmbase.modules.business.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.kotlinmvvmbase.core.base.viewmodel.BaseFragmentViewModel
import com.example.kotlinmvvmbase.core.deprecated.network_deprecated.model.response.datamodel.parts.PartsList
import com.example.kotlinmvvmbase.modules.business.repository.CarRepository

class HomeInternalVM(val value: String, private val repository: CarRepository) : BaseFragmentViewModel() {
    val carLiveData: MutableLiveData<PartsList> = MutableLiveData()

    init {
        Log.e("YASSIEN", "$value: View Model Created")
    }

    fun getData() {
//        viewModelScope.launch {
//            showLoading()
//            repository.getData(RequestHandler(PartsList::class.java, object : NetworkResult<PartsList> {
//                override fun onSuccess(t: PartsList) {
//                    hideLoading()
//                    carLiveData.postValue(t)
//                }
//
//                override fun onError(apiError: APIError) {
//                    hideLoading()
//                    handleError(apiError)
//                }
//
//                override fun onException(error: Throwable) {
//                    hideLoading()
//                    handleException(error)
//                }
//            }))
//        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.e("YASSIEN", "$value: View Model Destroyed")
    }
}