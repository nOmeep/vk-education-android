package com.example.viewed.ui.fragments.lists

import android.os.Bundle
import android.view.View
import com.example.viewed.api.items.MoviePage
import com.example.viewed.databinding.FragmentListsBinding
import com.example.viewed.ui.fragments.BaseFragment
import com.example.viewed.ui.fragments.lists.ListItemsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListsFragment : BaseFragment<FragmentListsBinding>(FragmentListsBinding::inflate) {
    /*private val films: ArrayList<MoviePage.Info>? = null
    private val listItemsAdapter = ListItemsAdapter(films!!)*/
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
