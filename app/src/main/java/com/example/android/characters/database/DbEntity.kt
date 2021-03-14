package com.example.android.characters.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="all_characters")
data class DbEntity(
    @PrimaryKey//(autoGenerate = true)
    var charId: Long = 0L,

    @ColumnInfo  //(name = "firstUrl")
    var firstUrl: String = "",

    @ColumnInfo  //(name = "icon")
    var icon: String = "",

    @ColumnInfo  //(name = "result")
    var result: String = "",

    @ColumnInfo  //(name = " text")
    var text: String = "",

    @ColumnInfo
    var name: String = ""
)