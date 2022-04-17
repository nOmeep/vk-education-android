package com.example.viewed.api.repo

import com.example.viewed.BuildConfig
import com.example.viewed.api.TheMovieDatabaseAPI
import com.example.viewed.api.items.SingleMovie
import com.example.viewed.db.CardsDB
import com.example.viewed.db.items.CardsViewed
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val api: TheMovieDatabaseAPI,
    db: CardsDB
) {
    private val profileDAO = db.cardsItemsQuery()
    suspend fun findMoviesByWatch(): MutableList<SingleMovie> {
        val listMovies = mutableListOf<SingleMovie>()
        profileDAO.getAllCardsWatch().forEach { cardsWatch ->
            listMovies.add(api.findMovieById(cardsWatch.Id, BuildConfig.API_KEY))
        }
        return listMovies
    }

    suspend fun findMoviesByViewed(): MutableList<SingleMovie> {
        val listMovies = mutableListOf<SingleMovie>()
        profileDAO.getAllCardsViewed().forEach { cardsViewed ->
            listMovies.add(api.findMovieById(cardsViewed.Id, BuildConfig.API_KEY))
        }
        return listMovies
    }

    suspend fun findMoviesByLater(): MutableList<SingleMovie> {
        val listMovies = mutableListOf<SingleMovie>()
        profileDAO.getAllCardsLater().forEach { cardsLater ->
            listMovies.add(api.findMovieById(cardsLater.Id, BuildConfig.API_KEY))
        }
        return listMovies
    }

    fun insert() {
        profileDAO.addCardViewed(CardsViewed(100))
    }
}
