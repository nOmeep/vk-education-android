package com.example.viewed.api.repo

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.viewed.BuildConfig
import com.example.viewed.api.TheMovieDatabaseAPI
import com.example.viewed.api.items.MoviePage
import retrofit2.HttpException
import java.io.IOException

class SearchPagingSource(
    private val api: TheMovieDatabaseAPI,
    private val query: String
) : PagingSource<Int, MoviePage.Info>() {
    companion object {
        const val START_PAGE_NUMBER = 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MoviePage.Info> {
        val position = params.key ?: START_PAGE_NUMBER

        return try {
            val response = api.searchForMovies(BuildConfig.API_KEY, query, position)

            LoadResult.Page(
                data = response.results,
                prevKey = if (position == START_PAGE_NUMBER) null else position - 1,
                nextKey = if (response.results.isEmpty()) null else position + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MoviePage.Info>): Int? = null
}
