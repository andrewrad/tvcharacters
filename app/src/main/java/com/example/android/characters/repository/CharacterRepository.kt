package com.example.android.characters.repository

import com.example.android.characters.DisplayOrderEnum
import com.example.android.characters.api.CharacterApi
import com.example.android.characters.api.CharacterProperties
import com.example.android.characters.api.ParentJsonObject
import com.example.android.characters.database.CharacterDatabase
import com.example.android.characters.database.DbEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class CharacterRepository(private val database : CharacterDatabase) {

    suspend fun getCharactersFromNetwork(){
        withContext(Dispatchers.IO){
            try{
                val parentObj: ParentJsonObject
                parentObj = CharacterApi.retrofitService.getSimpsonsCharacters()
                val characters = parentObj.RelatedTopics
                val readyForDb = parse(characters)
                database.DatabaseDao.insert(readyForDb)
            }catch(e:Exception){}
        }
    }

    fun parse(receivedCharacterList: List<CharacterProperties>) : List<DbEntity>{
        val _entityList = ArrayList<DbEntity>()
        var id = 5L

        for(it in receivedCharacterList){

            var extractedName = it.Result
            extractedName = extractedName.substring(extractedName.indexOf(">") + 1)  //https://stackoverflow.com/questions/12595019/how-to-get-a-string-between-two-characters
            extractedName = extractedName.substring(0, extractedName.indexOf("<"))

            val entity = DbEntity(
                charId = id,
                firstUrl = it.FirstURL,
                icon = it.Icon.URL,
                result = it.Result,
                text = it.Text,
                name = extractedName
            )
            id += 1L
            _entityList.add(entity)
        }
        return _entityList
    }

    suspend fun searchCharactersFromDb(query: String): List<DbEntity>{
        var data: List<DbEntity>
        withContext(Dispatchers.IO) {
            try {
                data = database.DatabaseDao.getAllNamesFromDbFTS(query)
            } catch (e: Exception) {
                var d = ArrayList<DbEntity>()
                d.add(DbEntity(0L, "", "", "", "", "None"))
                data = d
            }
        }
        return data
    }

    suspend fun getCharactersFromDb(selection: DisplayOrderEnum): List<DbEntity> {
        var data: List<DbEntity>
        withContext(Dispatchers.IO){
            try{
                when(selection) {
                    DisplayOrderEnum.ASCENDING -> {
                        data = database.DatabaseDao.getAllNamesFromDbAsc()
                    }
                    DisplayOrderEnum.DESCENDING -> {
                        data = database.DatabaseDao.getAllNamesFromDbDesc()
                    }
                    else -> {
                        data = database.DatabaseDao.getAllNamesFromDb() //Todo:
                    }
                }
            }catch(e:Exception){  //TODO:
                var d = ArrayList<DbEntity>()
                d.add(DbEntity(0L, "", "", "", "", ""))
                data = d
            }
        }
        return data
    }
}