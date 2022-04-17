package com.example.viewed.ui.fragments.viewmodels

import androidx.lifecycle.*
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
    private val currentQuery = MutableLiveData("PIE")

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
