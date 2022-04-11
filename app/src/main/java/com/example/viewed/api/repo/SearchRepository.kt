package com.example.viewed.api.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.viewed.api.TheMovieDatabaseAPI
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val api: TheMovieDatabaseAPI
) {
    fun findMoviesByQuery(query: String) =
        Pager(
            config = PagingConfig(
                pageSize = 10,
                maxSize = 50,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                SearchPagingSource(api, query)
            }
        ).liveData
}
