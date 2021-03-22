package com.example.android.characters.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.android.characters.R
import com.example.android.characters.databinding.FragmentDetailBinding
import com.example.android.characters.ui.TvCharacterUiModel
import com.example.android.characters.ui.main.MainFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment() {

    private val viewModel: MainFragmentViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentDetailBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)

        binding.lifecycleOwner = this

        binding.tvCharacter = TvCharacterUiModel(0L,"","","","","") //dummy character set until observer collects new data

        viewModel.charDetails.observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.tvCharacter = it
                viewModel.visited()

                constraintLayout2.setTransition(R.id.start, R.id.end)
                constraintLayout2.setTransitionDuration(1000)
                constraintLayout2.transitionToEnd()
            }
        })
        return binding.root
    }
}