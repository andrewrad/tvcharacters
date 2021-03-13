package com.example.android.characters.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.android.characters.R
import com.example.android.characters.database.CharacterDatabase
import com.example.android.characters.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: FragmentMainBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = CharacterDatabase.getInstance(application).DatabaseDao
        val viewModelFactory = MainFragmentViewModelFactory(dataSource, application)

        val viewModel = ViewModelProvider(this, viewModelFactory).get(MainFragmentViewModel::class.java)

        binding.mainFragmentViewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root

    }
}