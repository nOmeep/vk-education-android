package com.example.viewed.ui.fragments.profile

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.viewed.R
import com.example.viewed.databinding.FragmentProfileBinding
import com.example.viewed.ui.fragments.BaseFragment
import com.example.viewed.ui.fragments.viewmodels.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {
    private val viewModel by viewModels<ProfileViewModel>()
    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = view.findNavController()
        navController.navigateUp()

        val profileMoviesAdapter = ProfileItemsAdapter { x: Int -> viewModel.delMoviesByViewed(x) }

        viewModel.getMoviesLiveData().observe(viewLifecycleOwner) {
            profileMoviesAdapter.submitData(it)
        }

        binding.rvProfileResult.adapter = profileMoviesAdapter

        binding.viewed.setOnClickListener {
            profileMoviesAdapter.setDelete { x: Int -> viewModel.delMoviesByViewed(x) }
            viewModel.switchMoviesViewed()
        }

        binding.later.setOnClickListener {
            profileMoviesAdapter.setDelete { x: Int -> viewModel.delMoviesByLater(x) }
            viewModel.switchMoviesLater()
        }

        binding.watch.setOnClickListener {
            profileMoviesAdapter.setDelete { x: Int -> viewModel.delMoviesByWatch(x) }
            viewModel.switchMoviesWatch()
        }

        binding.exitOrLogin.setOnClickListener {
            Log.w(ContentValues.TAG, "signInWithEmail:failure")
            navController.navigate(R.id.action_profileFragment_to_loginFragment)
        }

        binding.email.setText(viewModel.getUserName())
    }
}
