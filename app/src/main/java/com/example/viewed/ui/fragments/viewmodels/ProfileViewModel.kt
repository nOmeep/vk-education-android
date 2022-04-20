package com.example.viewed.ui.fragments.viewmodels

import androidx.lifecycle.*
import com.example.viewed.api.repo.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: ProfileRepository
) : ViewModel() {
    fun getMoviesLiveData() = repository.findMoviesLiveData()

    fun switchMoviesWatch() {
        viewModelScope.launch {
            repository.findMoviesByWatch()
        }
    }

    fun switchMoviesViewed() {
        viewModelScope.launch {
            repository.findMoviesByViewed()
        }
    }

    fun switchMoviesLater() {
        viewModelScope.launch {
            repository.findMoviesByLater()
        }
    }

    fun delMoviesByWatch(id: Int) {
        repository.delMoviesByWatch(id)
    }

    fun delMoviesByViewed(id: Int) {
        repository.delMoviesByViewed(id)
    }

    fun delMoviesByLater(id: Int) {
        repository.delMoviesByLater(id)
    }
}
