package com.example.viewed.ui.fragments.search.results

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.viewed.R
import com.example.viewed.databinding.FragmentSearchResultPersonBinding

class SearchResultPersonFragment : Fragment(R.layout.fragment_search_result_person) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = FragmentSearchResultPersonBinding.bind(view)
    }
}
