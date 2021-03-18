package com.example.android.characters.main

import android.app.Application
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.characters.DisplayOrderEnum
import com.example.android.characters.database.CharacterDatabase
import com.example.android.characters.database.DatabaseDao
import com.example.android.characters.database.DbEntity
import com.example.android.characters.repository.CharacterRepository
import com.example.android.characters.repository.TvCharacter
import kotlinx.coroutines.launch
import java.io.IOException
import java.lang.Exception

class MainFragmentViewModel(val database: DatabaseDao, application: Application) : ViewModel()  {

    private val characterRepository = CharacterRepository(CharacterDatabase.getInstance(application))

    var charactersFromDb = MutableLiveData<List<DbEntity>>()  //observed in MainFragment as recyclerView Adapter

    private var _charDetails = MutableLiveData<TvCharacter?>()
    val charDetails
        get() = _charDetails

    val _userQuery = MutableLiveData<String>()

    init{
        getCharacterDataFromNetwork()
        updateFilter(DisplayOrderEnum.ASCENDING)
    }

    fun getCharacterDataFromNetwork(){
        viewModelScope.launch {
            try{
                characterRepository.getCharactersFromNetwork()
            } catch(e:IOException){}
        }
    }

    fun onCharacterClicked(id: DbEntity) {
        _charDetails.value = id.toTvCharacter()
    }

    fun visited() {
        _charDetails.value = null
    }

    fun userSearchFunction(searchView: SearchView){
        searchView.setOnQueryTextListener(object :  SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                _userQuery.value = newText

                queryPartialSearch(newText)

                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }
        })
    }

    // search performed in app search bar
    private fun queryPartialSearch(query: String){
        viewModelScope.launch {
            try{
                characterRepository.searchCharactersFromDb("*$query*").let{charactersFromDb.value = it}
            } catch(e:IOException){}
        }
    }

    fun updateFilter(selection: DisplayOrderEnum){
        viewModelScope.launch {
            try{
                charactersFromDb.value = characterRepository.getCharactersFromDb(selection)
            } catch(e:Exception){

            }
        }
    }
}