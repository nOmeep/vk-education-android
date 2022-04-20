package com.example.viewed.ui.fragments.search

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import com.example.viewed.databinding.FragmentSearchBinding
import com.example.viewed.ui.fragments.BaseFragment
import com.example.viewed.ui.fragments.search.listeners.QueryChangedListener
import com.example.viewed.ui.fragments.viewmodels.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {
    private val viewModel by viewModels<SearchViewModel>()

    private val searchedMoviesAdapter = SearchedItemsAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etSearch.addTextChangedListener(
            QueryChangedListener { str: String ->
                viewModel.switchQuery(str)
                binding.rvSearchResult.scrollToPosition(0)
            }
        )

        binding.rvSearchResult.adapter = searchedMoviesAdapter

        searchedMoviesAdapter.addLoadStateListener { loadStates ->
            binding.rvSearchResult.isVisible = loadStates.source.refresh is LoadState.NotLoading
            binding.pbLoading.isVisible = loadStates.source.refresh is LoadState.Loading
            binding.tvErrorMessage.isVisible = loadStates.source.refresh is LoadState.Error
            binding.btnRetry.isVisible = loadStates.source.refresh is LoadState.Error
        }

        binding.btnRetry.setOnClickListener {
            searchedMoviesAdapter.retry()
        }

        viewModel.getMovies().observe(viewLifecycleOwner) {
            searchedMoviesAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }
}
