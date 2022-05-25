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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val api: TheMovieDatabaseAPI,
    private val db: CardsDB,
) {
    private val profileDAO = db.cardsItemsQuery()
    private var profileFirebaseCloud: FirebaseFirestore = Firebase.firestore
    private val current = MutableLiveData<List<SingleMovie>>()
    private val emailUser = FirebaseAuth.getInstance().currentUser?.email
    private val userEmpty = hashMapOf(
        "Watch" to ArrayList<Int>(),
        "Later" to ArrayList<Int>(),
        "Viewed" to ArrayList<Int>()
    )

    fun findMoviesLiveData(): LiveData<List<SingleMovie>> {
        return current
    }

    suspend fun findMoviesByWatch() {
        val listMovies = mutableListOf<SingleMovie>()
        if (emailUser == null) {
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
        } else {
            profileFirebaseCloud.collection("FilmsByUser").document(emailUser).get()
                .addOnSuccessListener { documents ->
                    GlobalScope.launch {
                        val data = documents.data as Map<String, List<Int>>?
                        if (data != null && data.get("Watch") !== null) {
                            data.get("Watch")!!.forEach { cardsWatch ->
                                try {
                                    listMovies.add(
                                        api.findMovieById(
                                            cardsWatch,
                                            BuildConfig.API_KEY
                                        )
                                    )
                                } catch (e: IOException) {
                                    print(e)
                                } catch (e: HttpException) {
                                    print(e)
                                }
                            }
                        }
                        current.postValue(listMovies)
                    }
                }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    suspend fun findMoviesByViewed() {
        val listMovies = mutableListOf<SingleMovie>()
        if (emailUser == null) {
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
        } else {
            profileFirebaseCloud.collection("FilmsByUser").document(emailUser).get()
                .addOnSuccessListener { documents ->
                    GlobalScope.launch {
                        val data = documents.data as Map<String, List<Int>>?
                        if (data !== null && data.get("Viewed") !== null) {
                            data.get("Viewed")!!.forEach { cardsViewed ->
                                try {
                                    listMovies.add(
                                        api.findMovieById(
                                            cardsViewed,
                                            BuildConfig.API_KEY
                                        )
                                    )
                                } catch (e: IOException) {
                                    print(e)
                                } catch (e: HttpException) {
                                    print(e)
                                }
                            }
                        }
                        current.postValue(listMovies)
                    }
                }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    suspend fun findMoviesByLater() {
        val listMovies = mutableListOf<SingleMovie>()
        if (emailUser == null) {
            profileDAO.getAllCardsViewed().value?.forEach { cardsLater ->
                try {
                    listMovies.add(api.findMovieById(cardsLater.Id, BuildConfig.API_KEY))
                } catch (e: IOException) {
                    print(e)
                } catch (e: HttpException) {
                    print(e)
                }
            }
            current.value = listMovies
        } else {
            profileFirebaseCloud.collection("FilmsByUser").document(emailUser).get()
                .addOnSuccessListener { documents ->
                    GlobalScope.launch {
                        val data = documents.data as Map<String, List<Int>>?
                        if (data != null && data.get("Later") !== null) {
                            data.get("Later")!!.forEach { cardsLater ->
                                try {
                                    listMovies.add(
                                        api.findMovieById(
                                            cardsLater,
                                            BuildConfig.API_KEY
                                        )
                                    )
                                } catch (e: IOException) {
                                    print(e)
                                } catch (e: HttpException) {
                                    print(e)
                                }
                            }
                        }
                        current.postValue(listMovies)
                    }
                }
        }
    }

    fun insertMoviesByWatch(id: Int) {
        if (emailUser == null) {
            profileDAO.addCardWatch(CardsWatch(id))
        } else {
            profileFirebaseCloud.collection("FilmsByUser").document(emailUser).get()
                .addOnSuccessListener {
                    val tmp = it.data as Map<String, IntArray>?
                    if (tmp == null) {
                        profileFirebaseCloud.collection("FilmsByUser").document(emailUser)
                            .set(userEmpty).addOnSuccessListener {
                                profileFirebaseCloud.collection("FilmsByUser").document(emailUser)
                                    .update("Watch", FieldValue.arrayUnion(id))
                            }
                    } else {
                        profileFirebaseCloud.collection("FilmsByUser").document(emailUser)
                            .update("Watch", FieldValue.arrayUnion(id))
                    }
                }
        }
    }

    fun insertMoviesByViewed(id: Int) {
        if (emailUser == null) {
            profileDAO.addCardViewed(CardsViewed(id))
        } else {
            profileFirebaseCloud.collection("FilmsByUser").document(emailUser).get()
                .addOnSuccessListener {
                    val tmp = it.data as Map<String, IntArray>?
                    if (tmp == null) {
                        profileFirebaseCloud.collection("FilmsByUser").document(emailUser)
                            .set(userEmpty).addOnSuccessListener {
                                profileFirebaseCloud.collection("FilmsByUser").document(emailUser)
                                    .update("Viewed", FieldValue.arrayUnion(id))
                            }
                    } else {
                        profileFirebaseCloud.collection("FilmsByUser").document(emailUser)
                            .update("Viewed", FieldValue.arrayUnion(id))
                    }
                }
        }
    }

    fun insertMoviesByLater(id: Int) {
        if (emailUser == null) {
            profileDAO.addCardLater(CardsLater(id))
        } else {
            profileFirebaseCloud.collection("FilmsByUser").document(emailUser).get()
                .addOnSuccessListener {
                    val tmp = it.data as Map<String, IntArray>?
                    if (tmp == null) {
                        profileFirebaseCloud.collection("FilmsByUser").document(emailUser)
                            .set(userEmpty).addOnSuccessListener {
                                profileFirebaseCloud.collection("FilmsByUser").document(emailUser)
                                    .update("Later", FieldValue.arrayUnion(id))
                            }
                    } else {
                        profileFirebaseCloud.collection("FilmsByUser").document(emailUser)
                            .update("Later", FieldValue.arrayUnion(id))
                    }
                }
        }
    }

    suspend fun delMoviesByWatch(id: Int) {
        if (emailUser === null) {
            profileDAO.deleteCardWatch(id)
        } else {
            profileFirebaseCloud.collection("FilmsByUser").document(emailUser)
                .update("Watch", FieldValue.arrayRemove(id))
            findMoviesByWatch()
        }
    }

    suspend fun delMoviesByViewed(id: Int) {
        if (emailUser === null) {
            profileDAO.deleteCardViewed(id)
        } else {
            profileFirebaseCloud.collection("FilmsByUser").document(emailUser)
                .update("Viewed", FieldValue.arrayRemove(id))
            findMoviesByViewed()
        }
    }

    suspend fun delMoviesByLater(id: Int) {
        if (emailUser === null) {
            profileDAO.deleteCardLater(id)
        } else {
            profileFirebaseCloud.collection("FilmsByUser").document(emailUser)
                .update("Later", FieldValue.arrayRemove(id))
            findMoviesByLater()
        }
    }
}
