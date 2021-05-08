package com.example.kotlinmvvmbase.modules.business.viewmodel

import android.util.Log
import com.example.kotlinmvvmbase.core.base.viewmodel.BaseFragmentViewModel
import com.example.kotlinmvvmbase.modules.business.repository.CarRepository
import kotlinx.coroutines.*
import java.net.SocketTimeoutException
import kotlin.system.measureTimeMillis


class HomeFragmentVM : BaseFragmentViewModel() {
    private val repo = CarRepository(scope.coroutineContext.job, Dispatchers.IO)

    fun login() {
        scope.launch {
//            val response = async { repo.loginDriver() }
            val response = async { repo.getUnAuthorized() }
        }
    }

    fun exceptionTest() {
        scope.launch {
            launch {
                delay(500)
                println("JOB HERE FIRST SUCCESS")
            }

            launch {
                delay(1000)
                throw SocketTimeoutException()
            }

            launch {
                delay(1500)
                println("JOB HERE THIRD SUCCESS")
            }

        }
    }

    fun vmTrial() {
        scope.launch {
//            print("YASSIEN : Thread: ${Thread.currentThread().name}")
//            Log.e("YASSIEN", "YASSIEN : Thread: ${Thread.currentThread().name}")
//            for (i in 1..3) {
//                delay(1000)
//                print("YASSIEN : $i second")
//                Log.e("Yassien", "YASSIEN : $i second")
//            }
            val time = measureTimeMillis {
                val result1 = repo.repoTrialAsync().await()
                val result2 = repo.repoTrialAsync().await()
                Log.e("YASSIEN", "result1 = ${result1} | result2 = ${result2}")
            }
            Log.e("YASSIEN", "Time = $time")
        }
//        repo.repoTrialAsync()

    }
}