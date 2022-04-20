package com.example.viewed.di

import android.app.Application
import androidx.room.Room
import com.example.viewed.db.CardsDB
import com.example.viewed.db.CardsDB.Companion.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DBModule {
    @Provides
    @Singleton
    fun provideCardsDatabase(app: Application) =
        Room.databaseBuilder(app, CardsDB::class.java, DATABASE_NAME)
            .build()
}
