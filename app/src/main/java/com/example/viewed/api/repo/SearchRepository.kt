package com.example.viewed.api.repo

import com.example.viewed.api.TheMovieDatabaseAPI
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val api: TheMovieDatabaseAPI
) {
    suspend fun getMoviePageByQuery(key: String, query: String) = api.searchForMovies(key, query)
}