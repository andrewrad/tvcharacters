package com.example.android.characters.api

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

//import kotlinx.parcelize.Parcelize  //old-> //import kotlinx.android.parcel.Parcelize

//classes used for retrofit parser
//due to coding practices some people like to separate classes into their own files if needed

data class ParentJsonObject(
//contains many attributes (20?) that may be usable in the future, these are the only ones that have content at time of app creation
//        val AbstractSource : String,
//        val AbstractURL : String,
//        val Heading : String,
        val RelatedTopics : List<CharacterProperties>,
//        val meta : String
)
data class CharacterProperties(
        val FirstURL: String,
        val Icon: Icon,
        val Result: String,
        val Text: String
)

data class Icon(val URL : String
)

