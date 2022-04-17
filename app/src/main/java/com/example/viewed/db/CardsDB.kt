package com.example.viewed.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.viewed.db.items.CardsLater
import com.example.viewed.db.items.CardsViewed
import com.example.viewed.db.items.CardsWatch

@Database(entities = [CardsViewed::class, CardsWatch::class, CardsLater::class], version = 1)
abstract class CardsDB : RoomDatabase() {
    companion object {
        const val DATABASE_NAME = "cards_database"
    }

    abstract fun cardsItemsQuery(): CardsItems
}

