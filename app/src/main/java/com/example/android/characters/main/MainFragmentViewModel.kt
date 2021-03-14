package com.example.android.characters.main

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.characters.database.CharacterDatabase
import com.example.android.characters.database.DatabaseDao
import com.example.android.characters.repository.CharacterRepository
import kotlinx.coroutines.launch
import java.io.IOException

class MainFragmentViewModel(val database: DatabaseDao, application: Application) : ViewModel()  {

    private val characterRepository = CharacterRepository(CharacterDatabase.getInstance(application))

    init{
        getCharacterData()
    }

    fun getCharacterData(){
        viewModelScope.launch {
            try{
                characterRepository.getCharacters()
            } catch(e:IOException){}
        }
    }
}