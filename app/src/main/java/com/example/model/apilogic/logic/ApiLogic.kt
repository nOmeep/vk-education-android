package com.example.model.apilogic.logic

import com.example.model.apilogic.TheMovieDatabaseAPI
import com.example.model.apilogic.items.MoviePageX
import com.example.model.apilogic.items.SingleMovie
import com.google.gson.Gson
import java.io.FileInputStream
import java.net.URL
import java.util.*

class ApiLogic() : TheMovieDatabaseAPI {
    enum class Language(val lang: String) {
        RU("ru-RU"),
        EU("en-EU"),
    }

    companion object {
        const val BASE_INFO_URL = "http://api.themoviedb.org/"
        var API_KEY = ""
    }

    init {
        API_KEY = getApiKey();
    }

    override suspend fun searchForMovies(query: String, language: Language): MoviePageX {
        val url = URL(BASE_INFO_URL + "3/search/movie/?api_key=" + API_KEY + "&language=" + language.lang + "&query=" + query + "&page=1&include_adult=false")
        val gson = Gson()
        return gson.fromJson(url.readText(), MoviePageX::class.java)
    }

    override suspend fun findMovieById(movieId: Int, language: Language): SingleMovie {
        val url = URL(BASE_INFO_URL + "3/movie/" + movieId.toString()  + "?api_key=" + API_KEY + "&language=" + language.lang)
        val gson = Gson()
        return gson.fromJson(url.readText(), SingleMovie::class.java)
    }

    override suspend fun topMovieWeek(language: Language): MoviePageX {
        val url = URL(BASE_INFO_URL + "3/trending/movie/week?api_key=" + API_KEY + "&language=" + language.lang)
        val gson = Gson()
        return gson.fromJson(url.readText(), MoviePageX::class.java)
    }

    fun getApiKey() : String
    {
        val fis = FileInputStream("local.properties")
        val property = Properties()
        property.load(fis)
        return property.getProperty("api.key")
    }
}