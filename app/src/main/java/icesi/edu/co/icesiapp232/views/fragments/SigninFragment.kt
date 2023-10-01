package icesi.edu.co.icesiapp232.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import icesi.edu.co.icesiapp232.databinding.FragmentSigninBinding
import icesi.edu.co.icesiapp232.views.AuthActivity

class SigninFragment : Fragment() {

    lateinit var binding:FragmentSigninBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSigninBinding.inflate(inflater, container, false)

        (activity as AuthActivity).loadFragment(AuthActivity.SIGN_UP)

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = SigninFragment()
    }
}