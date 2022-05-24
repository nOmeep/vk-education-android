package com.example.viewed.ui.fragments.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.viewed.R
import com.example.viewed.databinding.FragmentLoginBinding
import com.example.viewed.ui.fragments.BaseFragment
import com.example.viewed.ui.fragments.viewmodels.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {
    private val viewModel by viewModels<LoginViewModel>()
    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = view.findNavController()
        binding.loginButton.setOnClickListener {
            viewModel.signIn(
                binding.login.text.toString(), binding.password.text.toString(), { ok() }, { error: String -> error(error) }
            )
        }
        binding.registerButton.setOnClickListener {
            navController.navigate(R.id.action_loginFragment_to_registrationFragment)
        }
    }

    fun error(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
    }

    fun ok() {
        navController.navigate(R.id.action_loginFragment_to_profileFragment)
    }
}
