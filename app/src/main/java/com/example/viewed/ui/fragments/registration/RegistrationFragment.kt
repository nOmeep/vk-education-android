package com.example.viewed.ui.fragments.registration

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.viewed.R
import com.example.viewed.databinding.FragmentRegistrationBinding
import com.example.viewed.ui.fragments.BaseFragment
import com.example.viewed.ui.fragments.viewmodels.RegistrationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegistrationFragment :
    BaseFragment<FragmentRegistrationBinding>(FragmentRegistrationBinding::inflate) {
    private val viewModel by viewModels<RegistrationViewModel>()
    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = view.findNavController()

        binding.registerActionButton.setOnClickListener {
            viewModel.signUp(
                binding.emailRegister.text.toString(),
                binding.passwordRegister.text.toString(),
                binding.passwordRegisterRepeat.text.toString(),
                { ok() },
                { error: String -> error(error) }
            )
        }
    }

    fun error(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
    }

    fun ok() {
        navController.navigate(R.id.action_registrationFragment_to_profileFragment)
    }
}
