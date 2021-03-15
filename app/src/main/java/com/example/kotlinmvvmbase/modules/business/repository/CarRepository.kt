package com.example.kotlinmvvmbase.modules.business.repository

import com.example.kotlinmvvmbase.core.base.repository.BaseRepository
import com.example.kotlinmvvmbase.core.network.api_service.LookupAPI
import com.example.kotlinmvvmbase.core.network.model.response.datamodel.parts.PartsList
import com.example.kotlinmvvmbase.core.network_manual_parsing.client.Client
import com.example.kotlinmvvmbase.core.network_manual_parsing.api.EndPoint
import com.example.kotlinmvvmbase.core.network_manual_parsing.client.RequestHandler

class CarRepository : BaseRepository<LookupAPI>(LookupAPI::class.java) {

    fun getData(callback: RequestHandler<PartsList>) {
        val call = Client.connect.GET(EndPoint.TEST_ENDPOINT)
        call.enqueue(callback)
    }

}