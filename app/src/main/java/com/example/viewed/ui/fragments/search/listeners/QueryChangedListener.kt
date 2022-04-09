package com.example.viewed.ui.fragments.search.listeners

import android.widget.SearchView

class QueryChangedListener(
    private val switchQuery: (s: String) -> Unit
) : SearchView.OnQueryTextListener {
    override fun onQueryTextSubmit(p0: String?): Boolean {
        if (!p0.isNullOrBlank()) {
            switchQuery.invoke(p0)
        }
        return true
    }

    override fun onQueryTextChange(p0: String?) = false
}