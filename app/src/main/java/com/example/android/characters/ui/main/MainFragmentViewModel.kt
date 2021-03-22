package com.example.android.characters.ui.main

import androidx.appcompat.widget.SearchView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.characters.DisplayOrderEnum
import com.example.android.characters.repository.CharacterRepository
import com.example.android.characters.ui.TvCharacterUiModel
import com.example.android.characters.ui.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor(private val repo: CharacterRepository) :
    ViewModel() {

    private var _charactersFromDb =
        MutableLiveData<List<TvCharacterUiModel>>()  //observed in MainFragment as recyclerView Adapter
    val charactersFromDb: LiveData<List<TvCharacterUiModel>> = _charactersFromDb

    private var _charDetails = MutableLiveData<TvCharacterUiModel>()
    val charDetails: LiveData<TvCharacterUiModel> = _charDetails

    fun getCharacterDataFromNetwork() {
        viewModelScope.launch {
            try {
                repo.getCharactersFromNetwork() //fills db with data from network
                updateFilter(DisplayOrderEnum.ASCENDING)
            } catch (e: IOException) {
            }
        }
    }

    fun onCharacterClicked(id: TvCharacterUiModel) {
        _charDetails.value = id
    }

    fun visited() {
        _charDetails.value = null
    }

    fun userSearchFunction(searchView: SearchView) {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.isNotEmpty())
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
    private fun queryPartialSearch(query: String) {
        viewModelScope.launch {
            repo.searchCharactersFromDb("*$query*").let {
                _charactersFromDb.value = it.map { it -> it.toUiModel() }
            }
        }
    }

    fun updateFilter(selection: DisplayOrderEnum) {  //used in options selected
        viewModelScope.launch {
            _charactersFromDb.value = repo.getCharactersFromDb(selection).map { it.toUiModel() }
        }
    }
}