package icesi.edu.co.icesiapp232.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import icesi.edu.co.icesiapp232.R
import icesi.edu.co.icesiapp232.databinding.FragmentSignupBinding


class SignupFragment : Fragment() {

    private lateinit var binding:FragmentSignupBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignupBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = SignupFragment()
    }
}