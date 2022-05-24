package com.example.viewed.screens.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.viewed.data.retrofit.RetrofitRepository
import com.example.viewed.models.MoviesModel
import kotlinx.coroutines.launch
import retrofit2.Response

class MainFragmentViewModel: ViewModel() {
    private val repository = RetrofitRepository()
    val myMovies: MutableLiveData<Response<MoviesModel>> = MutableLiveData()

    fun getMovies() {
        viewModelScope.launch {

            myMovies.value = repository.getMovies()

        }

    }
}