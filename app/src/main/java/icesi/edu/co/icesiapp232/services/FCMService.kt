package icesi.edu.co.icesiapp232.services

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import icesi.edu.co.icesiapp232.util.NotificationUtil
import org.json.JSONObject

class FCMService : FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        val obj = JSONObject(message.data as Map<*, *>)
        val json = obj.toString()
        NotificationUtil.showNotification(
            this,
                    "Nuevo mensaje",
                    json
        )
    }
}