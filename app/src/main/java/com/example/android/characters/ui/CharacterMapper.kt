package com.example.android.characters.ui

import com.example.android.characters.database.DbEntity

fun DbEntity.toUiModel(): TvCharacterUiModel = TvCharacterUiModel(
    charId = charId,
    firstUrl = firstUrl,
    icon = icon,
    result = result,
    text = text,
    name = name
)