package com.example.kotlinmvvmbase.core.base.app

import android.app.Application
import com.example.kotlinmvvmbase.core.base.repository.Cache
import kotlinx.coroutines.*

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        CoroutineScope(Dispatchers.IO).launch {
            Cache.initialize(this@App)
        }
    }
}