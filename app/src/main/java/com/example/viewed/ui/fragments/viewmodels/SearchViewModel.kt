package com.example.viewed.ui.fragments.viewmodels

import androidx.lifecycle.* // ktlint-disable no-wildcard-imports
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.viewed.api.items.MoviePage.Info
import com.example.viewed.api.repo.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: SearchRepository
) : ViewModel() {
    private val currentQuery = MutableLiveData<String>()

    fun getMovies(): LiveData<PagingData<Info>> {
        return currentQuery.switchMap { queryString ->
            repository.findMoviesByQuery(queryString)
                .cachedIn(viewModelScope)
        }
    }

    fun switchQuery(query: String) {
        currentQuery.value = query
    }
}
