package icesi.edu.co.icesiapp232.views.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import icesi.edu.co.icesiapp232.databinding.FragmentSigninBinding
import icesi.edu.co.icesiapp232.viewmodels.AuthViewModel
import icesi.edu.co.icesiapp232.views.AuthActivity
import icesi.edu.co.icesiapp232.views.MainActivity

class SigninFragment : Fragment() {

    lateinit var binding:FragmentSigninBinding
    private val vm: AuthViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSigninBinding.inflate(inflater, container, false)
        binding.signupLink.setOnClickListener {
            val authActivity = activity as AuthActivity
            authActivity.loadFragment(authActivity.signupFragment)
        }
        binding.signinBtn.setOnClickListener {
            vm.signin(
                binding.loginUsernameInput.editText?.text.toString(),
                binding.loginPasswordInput.editText?.text.toString(),
            )
        }
        vm.authStateLV.observe(viewLifecycleOwner){ state ->
            if(state.isAuth){
                startActivity(Intent(requireContext(), MainActivity::class.java))
            }
        }



        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = SigninFragment()
    }
}