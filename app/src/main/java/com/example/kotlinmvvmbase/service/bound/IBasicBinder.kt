package com.example.kotlinmvvmbase.service.bound

import android.os.Binder
import android.os.IBinder

abstract class IBasicBinder : Binder() {
    abstract fun getService() : BasicBoundService

    abstract fun sendNotification(value: Int)

    abstract suspend fun taskWithReturnValueAsync(value: Int) : Int
}