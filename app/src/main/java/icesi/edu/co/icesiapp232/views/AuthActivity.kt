package icesi.edu.co.icesiapp232.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import icesi.edu.co.icesiapp232.databinding.ActivityAuthBinding
import icesi.edu.co.icesiapp232.databinding.ActivityMainBinding
import icesi.edu.co.icesiapp232.views.fragments.SigninFragment
import icesi.edu.co.icesiapp232.views.fragments.SignupFragment

class AuthActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityAuthBinding.inflate(layoutInflater)
    }

     val signinFragment by lazy {
        SigninFragment.newInstance()
    }

     val signupFragment by lazy {
        SignupFragment.newInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //Cargamos inicialmente el fragment de sign in
        loadFragment(signinFragment)
    }

    fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragment).commit()
    }
}