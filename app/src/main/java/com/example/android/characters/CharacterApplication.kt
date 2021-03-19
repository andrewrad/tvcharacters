package com.example.android.characters

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp
import okhttp3.Cache
import okhttp3.OkHttpClient

@HiltAndroidApp
class CharacterApplication : Application(){
    override fun onCreate() {
        super.onCreate()
//        setupOkHttpClient(this)
    }

//    @Provides  //TODO: Hilt
//    @Singleton
//    fun setupOkHttpClient(context: Context): OkHttpClient {
//        val cacheSize = 10 * 1024 * 1024 //10MB
//        val cache = Cache(context.cacheDir, cacheSize.toLong())
//        return OkHttpClient.Builder().cache(cache).build()
//    }

}