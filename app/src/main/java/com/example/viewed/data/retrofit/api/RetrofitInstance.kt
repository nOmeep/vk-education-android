package com.example.viewed.data.retrofit.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {


        private val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()


        }

        val api: ApiService by lazy {
            retrofit.create(ApiService::class.java)
        }

}