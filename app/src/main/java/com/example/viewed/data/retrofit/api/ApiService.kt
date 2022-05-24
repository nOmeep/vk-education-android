package com.example.viewed.data.retrofit.api

import com.example.viewed.models.MoviesModel
import com.example.viewed.models.UpcomingModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
    }

    @GET("https://api.themoviedb.org/3/movie/popular?api_key=f026792d5170f665c600817536f77d5e&language=en-US&page=1")
    suspend fun getPopularMovie(): Response<MoviesModel>

    @GET("https://api.themoviedb.org/3/movie/upcoming?api_key=f026792d5170f665c600817536f77d5e&language=en-US&page=1")
    suspend fun getUpcomingMovie(): Response<UpcomingModel>
}