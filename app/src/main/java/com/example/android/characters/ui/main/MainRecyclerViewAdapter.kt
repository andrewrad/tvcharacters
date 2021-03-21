package com.example.android.characters.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.characters.databinding.RecyclerviewListItemBinding
import com.example.android.characters.ui.TvCharacterUiModel

class MainRecyclerViewAdapter(val clickListener: ItemClickListener) :
    RecyclerView.Adapter<ViewHolder>() {
    var data = listOf<TvCharacterUiModel>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position], clickListener)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun data(it: List<TvCharacterUiModel>) {
        data = it
    }
}


class ViewHolder private constructor(val binding: RecyclerviewListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: TvCharacterUiModel, clickListener: ItemClickListener) {
        binding.tvChar = item  //bind to data variable in xml file
        binding.clickListener = clickListener  //bind to data variable in xml file
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): ViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = RecyclerviewListItemBinding.inflate(layoutInflater, parent, false)
            return ViewHolder(binding)
        }
    }
}


class ItemClickListener(val clickListener: (name: TvCharacterUiModel) -> Unit) {
    fun onClick(character: TvCharacterUiModel) = clickListener(character)
}




