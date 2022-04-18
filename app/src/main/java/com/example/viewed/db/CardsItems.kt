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

    @Query("delete from later where later.id = :id")
    fun deleteCardLater(id: Int)

    @Query("delete from viewed where viewed.id = :id")
    fun deleteCardViewed(id: Int)

    @Query("delete from watch where watch.id = :id")
    fun deleteCardWatch(id: Int)
}
