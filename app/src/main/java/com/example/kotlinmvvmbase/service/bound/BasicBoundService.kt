package com.example.kotlinmvvmbase.service.bound

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.example.kotlinmvvmbase.R
import com.example.kotlinmvvmbase.constant.Constant
import com.example.kotlinmvvmbase.modules.business.BusinessActivity
import kotlinx.coroutines.*

class BasicBoundService : Service() {
    private lateinit var notificationManager: NotificationManager
    private lateinit var coroutineScope: CoroutineScope
    private var counterJob: Job? = null

    override fun onCreate() {
        super.onCreate()
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return object : IBasicBinder() {
            override fun getService(): BasicBoundService {
                return this@BasicBoundService
            }

            override fun sendNotification(value: Int) {
                this@BasicBoundService.sendNotification(value)
            }

            override suspend fun taskWithReturnValueAsync(value: Int): Int {
                var result= -1
                coroutineScope.launch {
                    delay(2000)
                    result = value*10
                }
                return result
            }

        }
    }

    private fun buildServiceNotification(progress: Int): Notification {
        val pendingIntent: PendingIntent =
                Intent(this, BusinessActivity::class.java).let { notificationIntent ->
                    PendingIntent.getActivity(this, 0, notificationIntent, 0)
                }
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Notification.Builder(this, Constant.Notification.NOTIFICATION_CHANNEL_ID)
                    .setContentTitle("Title")
                    .setContentText("Downloading")
                    .setSmallIcon(R.drawable.ic_profile)
                    .setContentIntent(pendingIntent)
                    .setOnlyAlertOnce(true)
                    .setProgress(100, progress, false)
                    .build()
        } else {
            NotificationCompat.Builder(this, Constant.Notification.NOTIFICATION_CHANNEL_ID)
                    .setContentTitle("Title")
                    .setContentText("Downloading")
                    .setSmallIcon(R.drawable.ic_profile)
                    .setContentIntent(pendingIntent)
                    .setOnlyAlertOnce(true)
                    .setProgress(100, progress, false)
                    .build()
        }
    }

    private fun sendNotification(progress: Int) {
        notificationManager.notify(1, buildServiceNotification(progress))
    }



    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "SERVICE DESTROYED", Toast.LENGTH_LONG).show()
    }
}