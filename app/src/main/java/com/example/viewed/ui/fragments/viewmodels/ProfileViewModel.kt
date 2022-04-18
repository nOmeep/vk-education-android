package com.example.viewed.ui.fragments.viewmodels

import androidx.lifecycle.ViewModel
import com.example.viewed.api.items.SingleMovie
import com.example.viewed.api.repo.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: ProfileRepository
) : ViewModel() {
    suspend fun getMoviesByWatch(): MutableList<SingleMovie> {
        return repository.findMoviesByWatch()
    }

    suspend fun getMoviesByViewed(): MutableList<SingleMovie> {
        return repository.findMoviesByViewed()
    }

    suspend fun getMoviesByLater(): MutableList<SingleMovie> {
        return repository.findMoviesByLater()
    }

    fun insert() {
        repository.insertMoviesByViewed(10) // test
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
