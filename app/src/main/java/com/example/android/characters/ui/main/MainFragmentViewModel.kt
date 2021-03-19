package com.example.android.characters.ui.main

import android.app.Application
import android.util.Log
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.characters.DisplayOrderEnum
import com.example.android.characters.database.CharacterDatabase
import com.example.android.characters.database.DatabaseDao
import com.example.android.characters.database.DbEntity
import com.example.android.characters.repository.CharacterRepository
import com.example.android.characters.ui.TvCharacterUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor(private val repo : CharacterRepository) : ViewModel()  {

    var charactersFromDb = MutableLiveData<List<DbEntity>>()  //observed in MainFragment as recyclerView Adapter
//    val charactersFromDb: LiveData<List<DbEntity>> = _charactersFromDb

    private var _charDetails = MutableLiveData<TvCharacterUiModel>()
    val charDetails: LiveData<TvCharacterUiModel> = _charDetails

    fun getCharacterDataFromNetwork(){
        viewModelScope.launch {
            try{
                repo.getCharactersFromNetwork() //fills db with data from network
                updateFilter(DisplayOrderEnum.ASCENDING)
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
                if(newText.isNotEmpty())
                    queryPartialSearch(newText)
                else
                    updateFilter(DisplayOrderEnum.ASCENDING)
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
                repo.searchCharactersFromDb("*$query*").let{charactersFromDb.value = it}
            } catch(e:IOException){}
        }
    }

    fun updateFilter(selection: DisplayOrderEnum){
        viewModelScope.launch {
                charactersFromDb.value = repo.getCharactersFromDb(selection)
        }
    }
}