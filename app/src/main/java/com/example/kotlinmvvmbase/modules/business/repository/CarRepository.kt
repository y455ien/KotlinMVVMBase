package com.example.kotlinmvvmbase.modules.business.repository

import com.example.kotlinmvvmbase.core.base.repository.BaseRepository
import com.example.kotlinmvvmbase.core.network.RetroCallback
import com.example.kotlinmvvmbase.core.network.api_service.LookupAPI
import com.example.kotlinmvvmbase.core.network.model.response.datamodel.parts.PartsList

class CarRepository : BaseRepository<LookupAPI>(LookupAPI::class.java) {

    fun getData(callback: RetroCallback<PartsList>) {
        val call = api.getUnProcessableEntityError("1", "2")
        call.enqueue(callback)
    }

}