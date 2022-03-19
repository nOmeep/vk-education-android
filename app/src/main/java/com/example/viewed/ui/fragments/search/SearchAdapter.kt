package com.example.viewed.ui.fragments.search

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.viewed.ui.fragments.search.results.SearchResultMovieFragment
import com.example.viewed.ui.fragments.search.results.SearchResultPersonFragment

class SearchAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return if (position == 0)
            SearchResultMovieFragment()
        else
            SearchResultPersonFragment()
    }
}
