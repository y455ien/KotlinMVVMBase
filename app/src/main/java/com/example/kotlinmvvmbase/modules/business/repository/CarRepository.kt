package com.example.kotlinmvvmbase.modules.business.repository

import com.example.kotlinmvvmbase.core.base.repository.BaseRepository
import com.example.kotlinmvvmbase.core.network.model.response.datamodel.parts.PartsList
import com.example.kotlinmvvmbase.core.network_manual_parsing.client.Client
import com.example.kotlinmvvmbase.core.network_manual_parsing.api.EndPoint
import com.example.kotlinmvvmbase.core.network_manual_parsing.client.RequestHandler

class CarRepository : BaseRepository() {

    fun getData(callback: RequestHandler<PartsList>) {
        val call = client.connect.GET(EndPoint.TEST_ENDPOINT)
        call.enqueue(callback)
    }

//    fun changeLanguage() {
//        swapLanguage()
//    }

}