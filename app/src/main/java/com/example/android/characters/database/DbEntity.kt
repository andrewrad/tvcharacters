package com.example.android.characters.database

import androidx.room.*
import com.example.android.characters.ui.TvCharacterUiModel

@Entity(tableName="dbTable")
data class DbEntity(
    var charId: Long = 0L,
    var firstUrl: String = "",
    var icon: String = "",
    var result: String = "",
    var text: String = "",
    @PrimaryKey
    var name: String = ""
){
//    companion object {  //TODO:mapper
//        fun from(tvCharacterUiModel: TvCharacterUiModel): DbEntity {
//            return DbEntity(
//                tvCharacterUiModel.charId,
//                tvCharacterUiModel.firstUrl,
//                tvCharacterUiModel.icon,
//                tvCharacterUiModel.result,
//                tvCharacterUiModel.text,
//                tvCharacterUiModel.name
//            )
//        }
//    }
//
//    fun toTvCharacter(): TvCharacterUiModel {
//        return TvCharacterUiModel(charId, firstUrl, icon, result, text, name)
//    }
}