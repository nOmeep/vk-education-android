package com.example.viewed.ui.fragments.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.viewed.R
import com.example.viewed.databinding.FragmentProfileBinding
import com.example.viewed.ui.fragments.viewmodels.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile) {
    private var _binding: FragmentProfileBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel by viewModels<ProfileViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentProfileBinding.bind(view)

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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
