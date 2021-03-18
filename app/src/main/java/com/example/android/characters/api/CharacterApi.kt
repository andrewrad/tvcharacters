package com.example.android.characters.api

import com.example.android.characters.APPENDED_URL
import com.google.gson.GsonBuilder
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

const val BASE_URL = "https://duckduckgo.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val gson = GsonBuilder()
    .setLenient()
    .create()

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create(gson))
    .build()

object CharacterApi{
    val retrofitService : CharacterApiService by lazy{ retrofit.create(CharacterApiService::class.java)}
}

interface CharacterApiService {



    @GET(APPENDED_URL)
    suspend fun getSimpsonsCharacters() : ParentJsonObject  //TODO:

    @GET("/i/{image_name}")
    suspend fun getImage(@Path("image_name") image: String) //TODO:
}
