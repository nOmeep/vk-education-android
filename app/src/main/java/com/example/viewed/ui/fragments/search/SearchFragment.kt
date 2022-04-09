package com.example.viewed.ui.fragments.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.viewed.R
import com.example.viewed.databinding.FragmentSearchBinding
import com.example.viewed.ui.fragments.search.listeners.QueryChangedListener
import com.example.viewed.ui.fragments.viewmodels.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {
    private var _binding: FragmentSearchBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel by viewModels<SearchViewModel>()

    private val searchedMoviesAdapter = SearchedItemsAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSearchBinding.bind(view)

        binding.etSearch.setOnQueryTextListener(QueryChangedListener { str: String -> viewModel.switchQuery(str) })

        binding.rvSearchResult.adapter = searchedMoviesAdapter

        viewModel.getMovies().observe(viewLifecycleOwner) {
            searchedMoviesAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
