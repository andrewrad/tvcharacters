package com.example.android.characters.repository

import com.example.android.characters.DisplayOrderEnum
import com.example.android.characters.api.CharacterApi
import com.example.android.characters.api.CharacterProperties
import com.example.android.characters.api.CharacterResponse
import com.example.android.characters.database.CharacterDatabase
import com.example.android.characters.database.DbEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class CharacterRepository(private val database: CharacterDatabase) {

    suspend fun getCharactersFromNetwork() {
        withContext(Dispatchers.IO) {
            try {
                val parentObj: CharacterResponse
                parentObj = CharacterApi.retrofitService.getSimpsonsCharacters()
                val characters = parentObj.RelatedTopics
                val readyForDb = parse(characters)
                database.DatabaseDao.insert(readyForDb)
            } catch (e: Exception) {
            }
        }
    }

    fun parse(receivedCharacterList: List<CharacterProperties>): List<DbEntity> {
        val _entityList = ArrayList<DbEntity>()
        var id = 5L

        for (it in receivedCharacterList) {

            var extractedName = it.Result
            extractedName = extractedName?.substring(extractedName.indexOf(">") + 1)
            extractedName = extractedName?.substring(0, extractedName.indexOf("<"))

            val entity = DbEntity(
                charId = id,
                firstUrl = it.FirstURL ?: "",
                icon = it.Icon?.URL ?: "",
                result = it.Result ?: "",
                text = it.Text ?: "",
                name = extractedName ?: ""
            )
            id += 1L
            _entityList.add(entity)
        }
        return _entityList
    }

    suspend fun searchCharactersFromDb(query: String) =
        withContext(context = Dispatchers.IO) {
            database.DatabaseDao.getAllNamesFromDbFTS(query) //?: listOf()
        }

    suspend fun getCharactersFromDb(selection: DisplayOrderEnum) =
        withContext(context = Dispatchers.IO) {
            when (selection) {
                DisplayOrderEnum.ASCENDING -> database.DatabaseDao.getAllNamesFromDbAsc()
                DisplayOrderEnum.DESCENDING -> database.DatabaseDao.getAllNamesFromDbDesc()
                else -> database.DatabaseDao.getAllNamesFromDb()
            }
        }
}