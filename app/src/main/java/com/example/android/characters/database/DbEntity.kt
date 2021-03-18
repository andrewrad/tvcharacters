package com.example.android.characters.database

import androidx.room.*
import com.example.android.characters.repository.TvCharacter

@Entity(tableName="dbTable")
data class DbEntity(
    //@PrimaryKey//(autoGenerate = true)
    var charId: Long = 0L,

    //@ColumnInfo  //(name = "firstUrl")
    var firstUrl: String = "",

    //@ColumnInfo  //(name = "icon")
    var icon: String = "",

    //@ColumnInfo  //(name = "result")
    var result: String = "",

    //@ColumnInfo  //(name = " text")
    var text: String = "",

    @PrimaryKey
    var name: String = ""
){
    companion object {
        fun from(tvCharacter: TvCharacter): DbEntity {
            return DbEntity(
                tvCharacter.charId,
                tvCharacter.firstUrl,
                tvCharacter.icon,
                tvCharacter.result,
                tvCharacter.text,
                tvCharacter.name
            )
        }
    }

    fun toTvCharacter(): TvCharacter{
        return TvCharacter(charId, firstUrl, icon, result, text, name)
    }
}