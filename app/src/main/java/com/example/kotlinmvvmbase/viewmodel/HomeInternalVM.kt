package com.example.kotlinmvvmbase.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.kotlinmvvmbase.core.base.repository.BaseRepository
import com.example.kotlinmvvmbase.core.base.viewmodel.BaseViewModel
import com.example.kotlinmvvmbase.core.base.viewmodel.ViewModelCallback
import com.example.kotlinmvvmbase.core.network.RetroCallback
import com.example.kotlinmvvmbase.core.network.model.response.datamodel.error.ErrorList
import com.example.kotlinmvvmbase.core.network.model.response.datamodel.parts.PartsList
import com.example.kotlinmvvmbase.util.BaseCommunicator
import kotlinx.coroutines.launch

class HomeInternalVM(val value: String, val repository: BaseRepository) : BaseViewModel() {
    val carLiveData: MutableLiveData<PartsList> = MutableLiveData()

    init {
        Log.e("YASSIEN", "VM Created")
    }

    fun getData() {
        viewModelScope.launch {
            repository.getData(RetroCallback(object : ViewModelCallback<PartsList> {
                override fun onException(error: Throwable) {

                }

                override fun onError(error: ErrorList) {

                }

                override fun onSuccess(t: PartsList) {
                    carLiveData.postValue(t)
                }

            }))
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.e("YASSIEN", "VM Cleared")
    }
}