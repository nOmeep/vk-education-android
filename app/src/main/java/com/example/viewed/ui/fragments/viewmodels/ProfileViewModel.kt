package com.example.viewed.ui.fragments.viewmodels

import androidx.lifecycle.* // ktlint-disable no-wildcard-imports
import com.example.viewed.api.items.SingleMovie
import com.example.viewed.api.repo.ProfileRepository
import com.example.viewed.db.CardsDB
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
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

    suspend fun insert() {
        return repository.insert()
    }
}
