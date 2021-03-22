package com.example.android.characters.ui

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvCharacterUiModel (
    val charId: Long,
    val firstUrl: String,
    val icon: String,
    val result: String,
    val text: String,
    val name: String
): Parcelable {}