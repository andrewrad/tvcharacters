package com.example.android.characters.di

import android.content.Context
import com.example.android.characters.database.CharacterDatabase
import com.example.android.characters.repository.CharacterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModel {

    @Provides
    @Singleton
    fun providesRepository(
        @ApplicationContext context: Context
    ): CharacterRepository = CharacterRepository(CharacterDatabase.getInstance(context))
}