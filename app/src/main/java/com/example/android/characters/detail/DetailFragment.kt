package com.example.android.characters.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.android.characters.R
import com.example.android.characters.databinding.FragmentDetailBinding
import com.example.android.characters.repository.TvCharacter

class DetailFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {

        val binding: FragmentDetailBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)

        binding.lifecycleOwner = this

        val arguments = arguments?.get("selectedTvCharacter") as TvCharacter

        binding.tvCharacter = arguments

        return binding.root
    }
}