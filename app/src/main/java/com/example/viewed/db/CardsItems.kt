package com.example.viewed.db

import androidx.room.* // ktlint-disable no-wildcard-imports
import com.example.viewed.db.items.CardsLater
import com.example.viewed.db.items.CardsViewed
import com.example.viewed.db.items.CardsWatch

@Dao
interface CardsItems {
    @Query("select id from later")
    fun getAllCardsLater(): List<CardsLater>

    @Query("select id from viewed")
    fun getAllCardsViewed(): List<CardsViewed>

    @Query("select id from watch")
    fun getAllCardsWatch(): List<CardsWatch>

    @Insert(entity = CardsLater::class, onConflict = OnConflictStrategy.REPLACE)
    fun addCardLater(card: CardsLater)

    @Insert(entity = CardsViewed::class, onConflict = OnConflictStrategy.REPLACE)
    fun addCardViewed(card: CardsViewed)

    @Insert(entity = CardsWatch::class, onConflict = OnConflictStrategy.REPLACE)
    fun addCardWatch(card: CardsWatch)

    @Delete(entity = CardsLater::class)
    fun deleteCardLater(card: CardsLater)

    @Delete(entity = CardsViewed::class)
    fun deleteCardViewed(card: CardsViewed)

    @Delete(entity = CardsWatch::class)
    fun deleteCardWatch(card: CardsWatch)
}
