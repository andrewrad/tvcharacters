package com.example.android.characters.main

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.android.characters.DisplayOrderEnum
import com.example.android.characters.R
import com.example.android.characters.database.CharacterDatabase
import com.example.android.characters.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private val viewModel: MainFragmentViewModel by lazy {
        ViewModelProvider(this).get(MainFragmentViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: FragmentMainBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = CharacterDatabase.getInstance(application).DatabaseDao
        val viewModelFactory = MainFragmentViewModelFactory(dataSource, application)

        val viewModel = ViewModelProvider(this, viewModelFactory).get(MainFragmentViewModel::class.java)

        binding.mainFragmentViewModel = viewModel
        binding.lifecycleOwner = this

        val adapter = MainRecyclerViewAdapter(ItemClickListener { id ->
            viewModel.onCharacterClicked(id)      //action to happen when item clicked
        })

        binding.characterRecycler.adapter = adapter

        viewModel.charactersFromDb.observe(viewLifecycleOwner, Observer {
            it?.let{
                adapter.data(it)   //send data to the recyclerView adapter
            }
        })

        viewModel.charDetails.observe(viewLifecycleOwner, Observer {
            it?.let{
                this.findNavController().navigate(MainFragmentDirections.actionShowDetail(it))
                viewModel.visited()
            }
        })

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_options, menu)
        super.onCreateOptionsMenu(menu, inflater)

        val searchItem = menu.findItem(R.id.search)
        val searchView = searchItem.actionView as SearchView
        searchView.queryHint = "Search Here"

        viewModel.userSearchFunction(searchView)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var selection: DisplayOrderEnum
        when (item.itemId) {
            R.id.show_order_asc -> selection = DisplayOrderEnum.ASCENDING
            R.id.show_order_desc -> selection = DisplayOrderEnum.DESCENDING
            else -> selection = DisplayOrderEnum.RANDOM
        }
        viewModel.updateFilter(selection)

        return true
    }
}