package com.example.kotlinmvvmbase.service.bound

import android.content.ComponentName
import android.content.ServiceConnection
import android.os.IBinder

class ServiceCommunicator : ServiceConnection {
    private lateinit var communicationBinder : IBasicBinder
    private var isBound = false

    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        communicationBinder = service as IBasicBinder
        isBound = true
    }

    override fun onServiceDisconnected(name: ComponentName?) {
        isBound = false
    }

    suspend fun action1(value: Int) {
        performActionAsync { communicationBinder.taskWithReturnValueAsync(value) }
    }

    fun action2(value: Int) {
        performAction { communicationBinder.sendNotification(value) }
    }

    private fun performAction(action: () -> Unit) {
        if (isBound) action.invoke()
    }

    private suspend fun performActionAsync(action: suspend () -> Unit) {
        if (isBound) action.invoke()
    }
}