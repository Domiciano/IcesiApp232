package icesi.edu.co.icesiapp232.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.firebase.Timestamp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.app
import com.google.firebase.messaging.ktx.messaging
import icesi.edu.co.icesiapp232.databinding.ActivityMainBinding
import icesi.edu.co.icesiapp232.viewmodels.AuthViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.util.UUID

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var listener:ListenerRegistration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.messagesTV.movementMethod = ScrollingMovementMethod()

        requestPermissions(
            arrayOf(
                android.Manifest.permission.POST_NOTIFICATIONS
            ), 1
        )

        Firebase.messaging.subscribeToTopic("noti").addOnSuccessListener {
            Log.e(">>>","Suscrito")
        }

        val user = Firebase.auth.currentUser
        if (user == null) {
            startActivity(Intent(this, AuthActivity::class.java))
            finish()
            return
        }

        listener = Firebase.firestore.collection("messages")
            .orderBy("date")
            .limitToLast(10)
            .addSnapshotListener { data, error ->
                for (doc in data!!.documentChanges) {
                    if (doc.type == DocumentChange.Type.ADDED) {
                        val message = doc.document.toObject(Message::class.java)
                        Log.e(">>>",message.date.toString())
                        binding.messagesTV.append("${message.content}\n\n")
                    }
                }
            }

        binding.sendBtn.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {

                val message = hashMapOf(
                    "id" to UUID.randomUUID().toString(),
                    "content" to binding.messageET.text.toString(),
                    "author" to user?.uid,
                    "date" to FieldValue.serverTimestamp()
                )

                Firebase.firestore
                    .collection("messages")
                    .document(message["id"].toString())
                    .set(message).await()

            }


        }
    }

    override fun onDestroy() {
        listener.remove()
        super.onDestroy()
    }
}

data class Message(
    var content: String = "",
    var date:Timestamp = Timestamp.now()
)