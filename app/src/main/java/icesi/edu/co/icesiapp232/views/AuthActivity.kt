package icesi.edu.co.icesiapp232.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import icesi.edu.co.icesiapp232.databinding.ActivityAuthBinding
import icesi.edu.co.icesiapp232.databinding.ActivityMainBinding

class AuthActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityAuthBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}