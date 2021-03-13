package com.example.android.characters.main

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.android.characters.database.DatabaseDao

class MainFragmentViewModel(val database: DatabaseDao, application: Application) : ViewModel()  {
}