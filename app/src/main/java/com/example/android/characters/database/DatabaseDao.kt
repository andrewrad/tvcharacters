package com.example.android.characters.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface DatabaseDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(characterList: List<DbEntity>)
}