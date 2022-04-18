package com.example.viewed.ui.fragments.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.viewed.R
import com.example.viewed.databinding.FragmentProfileBinding
import com.example.viewed.ui.fragments.viewmodels.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile) {
    private var _binding: FragmentProfileBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel by viewModels<ProfileViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentProfileBinding.bind(view)

        runBlocking {
            viewModel.insert() // test
            val profileMoviesAdapter = ProfileItemsAdapter(
                viewModel.getMoviesByViewed(),
                viewModel::delMoviesByViewed
            )
            binding.rvProfileResult.adapter = profileMoviesAdapter
        }

        binding.viewed.setOnClickListener {
            runBlocking {
                val profileMoviesAdapter = ProfileItemsAdapter(
                    viewModel.getMoviesByViewed(),
                    viewModel::delMoviesByViewed
                )
                binding.rvProfileResult.adapter = profileMoviesAdapter
            }
        }

        binding.later.setOnClickListener {
            runBlocking {
                val profileMoviesAdapter = ProfileItemsAdapter(
                    viewModel.getMoviesByLater(),
                    viewModel::delMoviesByLater
                )
                binding.rvProfileResult.adapter = profileMoviesAdapter
            }
        }

        binding.watch.setOnClickListener {
            runBlocking {
                val profileMoviesAdapter = ProfileItemsAdapter(
                    viewModel.getMoviesByWatch(),
                    viewModel::delMoviesByWatch
                )
                binding.rvProfileResult.adapter = profileMoviesAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
