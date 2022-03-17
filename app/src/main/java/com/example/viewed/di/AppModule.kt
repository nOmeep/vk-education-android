package com.example.viewed.di

import com.example.viewed.api.TheMovieDatabaseAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideMovieDatabaseAPI() : TheMovieDatabaseAPI =
        Retrofit.Builder()
            .baseUrl(TheMovieDatabaseAPI.BASE_INFO_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TheMovieDatabaseAPI::class.java)
}