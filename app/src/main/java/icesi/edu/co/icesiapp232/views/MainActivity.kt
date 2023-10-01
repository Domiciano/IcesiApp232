package icesi.edu.co.icesiapp232.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import icesi.edu.co.icesiapp232.databinding.ActivityMainBinding
import icesi.edu.co.icesiapp232.viewmodels.AuthViewModel

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}