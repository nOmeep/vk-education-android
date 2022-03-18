package com.example.model.apilogic

import com.example.model.apilogic.items.MoviePageX
import com.example.model.apilogic.items.SingleMovie
import com.example.model.apilogic.logic.ApiLogic

interface TheMovieDatabaseAPI {
    companion object {
        const val BASE_INFO_URL = "https://api.themoviedb.org/"
    }

    suspend fun searchForMovies(
        query: String,
        language: ApiLogic.Language
    ): MoviePageX

    suspend fun findMovieById(
        movieId: Int,
        language: ApiLogic.Language
    ): SingleMovie


    suspend fun topMovieWeek(
        language: ApiLogic.Language
    ): MoviePageX
}