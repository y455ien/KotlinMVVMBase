package com.example.kotlinmvvmbase.modules.business.repository

import android.util.Log
import com.example.kotlinmvvmbase.core.base.repository.BaseRepository
import com.example.kotlinmvvmbase.core.network.WebResponse
import com.example.kotlinmvvmbase.core.network.model.response.datamodel.parts.PartsList
import com.example.kotlinmvvmbase.core.network_manual_parsing.client.Client
import com.example.kotlinmvvmbase.core.network_manual_parsing.api.EndPoint
import com.example.kotlinmvvmbase.core.network_manual_parsing.client.RequestHandler
import kotlinx.coroutines.*

class CarRepository(vmJob: Job = Job(), dispatcher: CoroutineDispatcher = Dispatchers.IO) : BaseRepository(vmJob, dispatcher) {

    fun getData(callback: RequestHandler<PartsList>) {
        val call = client.connect.GET(EndPoint.TEST_ENDPOINT)
        call.enqueue(callback)
    }

    suspend fun loginDriver(): WebResponse<String> {
        return  client.connect.coroutinePost("/api/v1/driver/auth/signin", hashMapOf("name" to "hazem.hamaad10@gmail.com", "password" to "Hh121212"))
    }

    suspend fun getUnAuthorized(): WebResponse<String> {
        return client.connect.coroutineGet("/api/v1/driver/auth/get-profile?api_token=c38127bd95227ac9b1dab4336a552d87")
    }

    fun repoTrialAsync(): Deferred<String> {
//        scope.launch {
//            print("YASSIEN : Thread: ${Thread.currentThread().name}")
//            Log.e("Yassien", "YASSIEN : Thread: ${Thread.currentThread().name}")
//            for (i in 1..3) {
//                delay(1000)
//                print("YASSIEN : $i second")
//                Log.e("YASSIEN", "YASSIEN : $i second")
//            }
//        }
        return scope.async {
            delay(3000)
            return@async "Result"
        }
    }


//    fun changeLanguage() {
//        swapLanguage()
//    }

}