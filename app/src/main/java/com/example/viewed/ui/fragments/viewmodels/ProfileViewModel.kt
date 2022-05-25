package com.example.viewed.ui.fragments.viewmodels

import androidx.lifecycle.*
import com.example.viewed.api.repo.ProfileRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: ProfileRepository,
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {
    private var email: String

    init {
        if (firebaseAuth.currentUser == null || firebaseAuth.currentUser?.email == null) {
            email = ""
        } else {
            email = firebaseAuth.currentUser!!.email.toString()
        }
    }

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
        viewModelScope.launch {
            repository.delMoviesByWatch(id)
        }
    }

    fun delMoviesByViewed(id: Int) {
        viewModelScope.launch {
            repository.delMoviesByViewed(id)
        }
    }

    fun delMoviesByLater(id: Int) {
        viewModelScope.launch {
            repository.delMoviesByLater(id)
        }
    }

    fun signOut() {
        return firebaseAuth.signOut()
    }

    fun getUserName(): String {
        return email
    }
}
