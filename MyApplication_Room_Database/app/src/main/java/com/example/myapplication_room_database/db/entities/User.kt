package com.reza.roomapplication.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @ColumnInfo val username: String,
    @ColumnInfo var password: String,
    @ColumnInfo(name = "first_name") var firstName: String,
    @ColumnInfo(name = "last_name") var lastName: String
) {
    @PrimaryKey(autoGenerate = true) var id: Long = 0

}