package com.example.viewed.ui.fragments.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.viewed.api.items.MoviePage
import com.example.viewed.api.repo.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: SearchRepository
) : ViewModel() {
    private val searchedMovies = MutableLiveData<MoviePage>()

    init {
        viewModelScope.launch {
            searchedMovies.value = repository.getMoviePageByQuery("5c517a179d559dfff951527fe14474bf", "pie")
        }
    }

    fun getSearchedMovies() = searchedMovies
}