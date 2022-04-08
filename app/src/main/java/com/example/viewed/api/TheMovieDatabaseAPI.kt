package com.example.viewed.api

import com.example.viewed.api.items.MoviePage
import com.example.viewed.api.items.SingleMovie
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieDatabaseAPI {
    companion object {
        const val BASE_INFO_URL = "https://api.themoviedb.org/3/"
    }

    @GET("search/movie")
    suspend fun searchForMovies(
        @Query("api_key") key: String,
        @Query("query") query: String,
        @Query("page") page: Int = 1,
        @Query("language") language: String = "ru-Rus",
        @Query("include_adult") includeAdult: Boolean = false,
    ): MoviePage

    @GET("movie/{movie_id}")
    suspend fun findMovieById(
        @Path("movie_id") movieId: Int,
        @Query("api_key") key: String,
        @Query("language") language: String = "ru-Rus"
    ): SingleMovie
}
