package com.example.android.characters.api

//import com.google.gson.GsonBuilder
import com.google.gson.GsonBuilder
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://duckduckgo.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

//private val retrofit = Retrofit.Builder()
//    .addConverterFactory(MoshiConverterFactory.create(moshi))
//    .baseUrl(BASE_URL)
//    .client(
//        OkHttpClient.Builder()
//            .connectTimeout(20, TimeUnit.SECONDS)
//            .readTimeout(20, TimeUnit.SECONDS)
//            .build()
//    )
//    .build()


//private val retrofit = Retrofit.Builder()
//    .addConverterFactory(MoshiConverterFactory.create(moshi))
//    .baseUrl(BASE_URL)
//    .build()


private val gson = GsonBuilder()
    .setLenient()
    .create()

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
//    .addConverterFactory(ScalarsConverterFactory.create())
    .addConverterFactory(GsonConverterFactory.create(gson))
    .build()

object CharacterApi{
    val retrofitService : CharacterApiService by lazy{ retrofit.create(CharacterApiService::class.java)}
}

interface CharacterApiService {

//    @GET()
//    suspend fun getSimpsonsCharacters(
//        @Query("q") String("simpsons+characters"),
//        @Query("format")): List<ParentJsonObject>

    @GET("?q=simpsons+characters&format=json")
    suspend fun getSimpsonsCharacters() : ParentJsonObject

    @GET("/i/{image_name}")
    suspend fun getImage(@Path("image_name") image: String)
}
