package com.example.viewed.ui.fragments.search.results

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.viewed.R
import com.example.viewed.databinding.FragmentSearchResultMovieBinding

class SearchResultMovieFragment : Fragment(R.layout.fragment_search_result_movie) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = FragmentSearchResultMovieBinding.bind(view)
    }
}
