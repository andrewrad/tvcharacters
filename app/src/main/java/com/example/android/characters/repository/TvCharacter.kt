package com.example.android.characters.repository

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvCharacter (
    val charId: Long,
    val firstUrl: String,
    val icon: String,
    val result: String,
    val text: String,
    val name: String
): Parcelable {}