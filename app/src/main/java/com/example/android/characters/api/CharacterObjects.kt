package com.example.android.characters.api

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

//import kotlinx.parcelize.Parcelize  //old-> //import kotlinx.android.parcel.Parcelize


data class CharacterResponse(
        val RelatedTopics : List<CharacterProperties>
)

data class CharacterProperties(
        val FirstURL: String?,
        val Icon: Icon?,
        val Result: String?,
        val Text: String?
)

data class Icon(val URL : String)

