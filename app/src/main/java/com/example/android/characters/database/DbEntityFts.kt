package com.example.android.characters.database

import androidx.room.Entity
import androidx.room.Fts4
import androidx.room.PrimaryKey

@Fts4(contentEntity = DbEntity::class)
@Entity(tableName = "dbTableFts")
class DbEntityFts(
    @PrimaryKey
    var rowid: Int,  //needed for Fts object
    var charId: Long = 0L,
    var firstUrl: String = "",
    var icon: String = "",
    var result: String = "",
    var text: String = "",
    var name: String = ""
) {}