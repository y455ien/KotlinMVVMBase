package com.example.kotlinmvvmbase.service.foreground

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
import okhttp3.internal.notify

class BasicForegroundService : Service() {
    private lateinit var notificationManager: NotificationManager
    private lateinit var coroutineScope: CoroutineScope
    private var counterJob: Job? = null

    override fun onCreate() {
        super.onCreate()
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startForeground(1, buildServiceNotification(0))
        mockAsyncTask()
        return super.onStartCommand(intent, flags, START_NOT_STICKY)
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

    private fun mockAsyncTask() {
        if (counterJob == null || counterJob!!.isCompleted) {
            counterJob = coroutineScope.launch {
                withContext(Dispatchers.IO) {
                    for (i in 1..100) {
                        updateNotification(i)
                        delay(1000)
                    }
                }
                stopSelf()
            }
        } else if (counterJob!!.isActive) Toast.makeText(this, "Task is not finished", Toast.LENGTH_LONG).show()
    }

    private fun updateNotification(progress: Int) {
        val updatedNotification = buildServiceNotification(progress)
        notificationManager.notify(1, updatedNotification)
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineScope.cancel()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}