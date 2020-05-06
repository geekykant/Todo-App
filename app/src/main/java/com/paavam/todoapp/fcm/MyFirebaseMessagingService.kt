package com.paavam.todoapp.fcm

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.paavam.todoapp.R
import java.security.SecureRandom
import java.util.*


class MyFirebaseMessagingService : FirebaseMessagingService() {

    private val TAG = "FirebaseService"

    override fun onMessageReceived(message: RemoteMessage?) {
        super.onMessageReceived(message)

        Log.i(TAG, message?.from!!)
        Log.i(TAG, message.notification?.body!!)

        showMessageNotification(message.notification)
    }

    private fun showMessageNotification(notification: RemoteMessage.Notification?) {
        val channelID = "TodoID"
        val ringtone = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val notificationBuilder = NotificationCompat.Builder(this, channelID)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle("Todo notes App")
                .setContentText(notification?.body)
                .setSound(ringtone)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(channelID, "General", NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(createRandomCode(3), notificationBuilder.build())
    }

    override fun onNewToken(token: String?) {
        super.onNewToken(token)

        Log.i(TAG, "token $token")
    }

    fun createRandomCode(codeLength: Int): Int {
        val chars = "1234567890".toCharArray()
        val sb = StringBuilder()
        val random: Random = SecureRandom()
        for (i in 0 until codeLength) {
            val c = chars[random.nextInt(chars.size)]
            sb.append(c)
        }
        return sb.toString().toInt()
    }
}