package com.example.android.characters.database

import androidx.room.*

@Entity(tableName="dbTable")
data class DbEntity(
    var charId: Long = 0L,
    var firstUrl: String = "",
    var icon: String = "",
    var result: String = "",
    var text: String = "",
    @PrimaryKey
    var name: String = ""
){}