package com.example.android.characters.repository

import com.example.android.characters.api.CharacterApi
import com.example.android.characters.api.CharacterProperties
import com.example.android.characters.api.ParentJsonObject
import com.example.android.characters.database.CharacterDatabase
import com.example.android.characters.database.DbEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class CharacterRepository(private val database : CharacterDatabase) {

    suspend fun getCharacters(){
        withContext(Dispatchers.IO){
            try{
                val parentObj: ParentJsonObject
                parentObj = CharacterApi.retrofitService.getSimpsonsCharacters()

                val characters = parentObj.RelatedTopics
                database.DatabaseDao.insert(parse(characters))

            }catch(e:Exception){}
        }
    }

    fun parse(receivedCharacterList: List<CharacterProperties>) : List<DbEntity>{
        val _entityList = ArrayList<DbEntity>()

        for(it in receivedCharacterList){
            val entity = DbEntity(
                firstUrl = it.FirstURL,
                icon = it.Icon.URL,
                result = it.Result,
                text = it.Text
            )
            _entityList.add(entity)
        }
        return _entityList
    }
}