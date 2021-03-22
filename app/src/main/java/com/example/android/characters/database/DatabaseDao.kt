package com.example.android.characters.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DatabaseDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(characterList: List<DbEntity>)

    @Query("select * from dbTable order by name desc")
    suspend fun getAllNamesFromDbDesc(): List<DbEntity>

    @Query("select * from dbTable order by name asc")
    suspend fun getAllNamesFromDbAsc(): List<DbEntity>

    @Query("select * from dbTable where icon != \"\" order by name asc")
    suspend fun getAllNamesFromDb(): List<DbEntity>

    @Query("SELECT * FROM dbTable JOIN dbTableFts ON dbTable.text == dbTableFts.text WHERE dbTableFts.text MATCH :query")
    suspend fun searchStringFromDbFTS(query: String): List<DbEntity>  //search titles or descriptions- search .text column since they are both found in .text
}
