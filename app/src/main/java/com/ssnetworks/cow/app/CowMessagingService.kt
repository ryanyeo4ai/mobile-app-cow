package com.ssnetworks.cow.app

import android.util.Log
import android.widget.Toast
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class CowMessagingService : FirebaseMessagingService() {
    private val tag: String = CowMessagingService::class.java.simpleName

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.e(tag, "Firebase NewToken: $token")
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        Toast.makeText(this, "Firebase notification body: ${message.notification?.body}", Toast.LENGTH_SHORT).show()
        Log.e(tag, "Firebase notification body: ${message.notification?.body}")
    }
}