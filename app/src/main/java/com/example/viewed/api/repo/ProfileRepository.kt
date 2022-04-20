package com.example.viewed.api.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.viewed.BuildConfig
import com.example.viewed.api.TheMovieDatabaseAPI
import com.example.viewed.api.items.SingleMovie
import com.example.viewed.db.CardsDB
import com.example.viewed.db.items.CardsLater
import com.example.viewed.db.items.CardsViewed
import com.example.viewed.db.items.CardsWatch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val api: TheMovieDatabaseAPI,
    db: CardsDB
) {
    private val profileDAO = db.cardsItemsQuery()
    private val current = MutableLiveData<List<SingleMovie>>()

    fun findMoviesLiveData(): LiveData<List<SingleMovie>> {
        return current
    }

    suspend fun findMoviesByWatch() {
        val listMovies = mutableListOf<SingleMovie>()
        profileDAO.getAllCardsWatch().value?.forEach { cardsWatch ->
            try {
                listMovies.add(api.findMovieById(cardsWatch.Id, BuildConfig.API_KEY))
            } catch (e: IOException) {
                print(e)
            } catch (e: HttpException) {
                print(e)
            }
        }
        current.value = listMovies
    }

    suspend fun findMoviesByViewed() {
        val listMovies = mutableListOf<SingleMovie>()
        profileDAO.getAllCardsViewed().value?.forEach { cardsViewed ->
            try {
                listMovies.add(api.findMovieById(cardsViewed.Id, BuildConfig.API_KEY))
            } catch (e: IOException) {
                print(e)
            } catch (e: HttpException) {
                print(e)
            }
        }
        current.value = listMovies
    }

    suspend fun findMoviesByLater() {
        val listMovies = mutableListOf<SingleMovie>()
        profileDAO.getAllCardsLater().value?.forEach { cardsLater ->
            try {
                listMovies.add(api.findMovieById(cardsLater.Id, BuildConfig.API_KEY))
            } catch (e: IOException) {
                print(e)
            } catch (e: HttpException) {
                print(e)
            }
        }
        current.value = listMovies
    }

    fun insertMoviesByWatch(id: Int) {
        profileDAO.addCardWatch(CardsWatch(id))
    }

    fun insertMoviesByViewed(id: Int) {
        profileDAO.addCardViewed(CardsViewed(id))
    }

    fun insertMoviesByLater(id: Int) {
        profileDAO.addCardLater(CardsLater(id))
    }

    fun delMoviesByWatch(id: Int) {
        profileDAO.deleteCardWatch(id)
    }

    fun delMoviesByViewed(id: Int) {
        profileDAO.deleteCardViewed(id)
    }

    fun delMoviesByLater(id: Int) {
        profileDAO.deleteCardLater(id)
    }
}
