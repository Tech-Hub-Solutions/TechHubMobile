package com.example.techhub.domain.notificacao

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.techhub.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class PushNotificationService : FirebaseMessagingService() {

    companion object{
        private const val TAG = "FCMNotification"
        const val DEFAULT_NOTIFICATION  = 0
    }

    override fun onNewToken(token: String) {
        Log.i(TAG,"new FCM token created: $token")
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        initNotificationChannel(notificationManager)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        val title = remoteMessage.notification?.title
        val body = remoteMessage.notification?.body

        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        var notificationBuilder =  if (Build.VERSION_CODES.O <= Build.VERSION.SDK_INT){
            NotificationCompat.Builder(applicationContext,"1")
        } else {
            NotificationCompat.Builder(applicationContext)
        }
        notificationBuilder = notificationBuilder
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(title)
            .setContentText(body)
            .setAutoCancel(true)
        initNotificationChannel(notificationManager)
        notificationManager.notify(DEFAULT_NOTIFICATION, notificationBuilder.build())
    }

    private fun initNotificationChannel(notificationManager: NotificationManager){
        if (Build.VERSION_CODES.O <= Build.VERSION.SDK_INT){
            notificationManager.createNotificationChanelIfNotExists(
                notificationManager,
                channelId = "1",
                channelName = "Default"
            )
        }
    }

}

@RequiresApi(Build.VERSION_CODES.O)
fun NotificationManager.createNotificationChanelIfNotExists(
    notificationManager: NotificationManager,
    channelId: String,
    channelName: String,
    importance: Int  = NotificationManager.IMPORTANCE_DEFAULT
){
    var channel = notificationManager.getNotificationChannel(channelId)

    if(channel == null){
        channel = NotificationChannel(
            channelId,
            channelName,
            importance
        )
        this.createNotificationChannel(channel)
    }
}


