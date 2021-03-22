package com.example.android.characters.api

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

