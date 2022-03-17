package com.example.viewed.api

import com.example.viewed.api.items.FilmPage
import retrofit2.http.GET
import retrofit2.http.Query

interface TheMovieDatabaseAPI {
    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
    }

    @GET("/search/movie")
    suspend fun searchForMovies(
        @Query("api_key") key: String,
        @Query("query") query: String,
        @Query("language") language: String = "ru-Rus",
        @Query("include_adult") includeAdult: Boolean = false,
    ) : FilmPage
}
