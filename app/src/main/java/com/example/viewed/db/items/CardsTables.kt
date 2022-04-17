package com.example.viewed.db.items

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "viewed")
data class CardsViewed(
    @PrimaryKey @ColumnInfo(name = "id")
    val Id: Int
)

@Entity(tableName = "watch")
data class CardsWatch(
    @PrimaryKey @ColumnInfo(name = "id")
    val Id: Int
)

@Entity(tableName = "later")
data class CardsLater(
    @PrimaryKey @ColumnInfo(name = "id")
    val Id: Int
)
