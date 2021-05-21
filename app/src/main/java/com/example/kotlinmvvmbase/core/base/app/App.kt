package com.example.kotlinmvvmbase.core.base.app

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.core.content.getSystemService
import com.example.kotlinmvvmbase.constant.Constant
import com.example.kotlinmvvmbase.core.base.repository.Cache
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class App : Application() {
    private val scope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    override fun onCreate() {
        super.onCreate()
        scope.launch {
            Cache.initialize(this@App)
            setupNotificationChannel()
        }
    }

    private fun setupNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel: NotificationChannel = NotificationChannel(
                Constant.Notification.NOTIFICATION_CHANNEL_ID,
                Constant.Notification.NOTIFICATION_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val notificationManager: NotificationManager =
                getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }
}