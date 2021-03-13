package com.example.kotlinmvvmbase.core.base.repository

import com.example.kotlinmvvmbase.core.network.RetroCallback
import com.example.kotlinmvvmbase.core.network.RetroInstance
import com.example.kotlinmvvmbase.core.network.api_service.LookupAPI
import com.example.kotlinmvvmbase.core.network.model.response.datamodel.parts.PartsList

class BaseRepository {

    fun getData(callback: RetroCallback<PartsList>) {
        val call = RetroInstance.getAPI(LookupAPI::class.java).getParts("1", "2")
        call.enqueue(callback)
    }
}