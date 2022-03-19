package com.example.viewed.ui.fragments.search.results

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.viewed.R
import com.example.viewed.databinding.FragmentSearchResultBinding

class SearchResultFragment : Fragment(R.layout.fragment_search_result) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = FragmentSearchResultBinding.bind(view)

        arguments?.takeIf { it.containsKey(("position")) }?.apply {
            binding.test.text = getInt("position").toString()
        }
    }
}
