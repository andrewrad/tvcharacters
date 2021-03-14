package com.example.android.characters.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DatabaseDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(characterList: List<DbEntity>)

    @Query("select * from all_characters order by name asc")
    fun getAllNames() : List<DbEntity>

}