package icesi.edu.co.icesiapp232.views.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import icesi.edu.co.icesiapp232.R
import icesi.edu.co.icesiapp232.databinding.FragmentSignupBinding
import icesi.edu.co.icesiapp232.viewmodels.AuthViewModel
import icesi.edu.co.icesiapp232.views.AuthActivity
import icesi.edu.co.icesiapp232.views.MainActivity


class SignupFragment : Fragment() {

    private lateinit var binding: FragmentSignupBinding
    private val vm: AuthViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignupBinding.inflate(inflater, container, false)

        binding.loginLink.setOnClickListener {
            val authActivity = activity as AuthActivity
            authActivity.loadFragment(authActivity.signinFragment)
        }
        binding.signupButton.setOnClickListener {
            vm.signup(
                binding.signupEmailInput.editText?.text.toString(),
                binding.signupPasswordInput.editText?.text.toString()
            )
        }

        //Observer
        vm.authStateLV.observe(viewLifecycleOwner){ state ->
            if(state.isAuth){
                startActivity(Intent(requireContext(), MainActivity::class.java))
            }
        }
        vm.errorLV.observe(viewLifecycleOwner){error->
            Toast.makeText(requireContext(), error.message, Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = SignupFragment()
    }
}