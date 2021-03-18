package com.example.android.characters

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.android.characters.api.BASE_URL
import com.example.android.characters.database.DbEntity
import com.example.android.characters.repository.TvCharacter


//Recycler View list item binding:

@BindingAdapter("textViewName")
fun TextView.setTextViewName(item: DbEntity?){
    item?.let{
        text = item.name
    }
}

//DetailFragment binding views:

@BindingAdapter("characterPicture")
fun bindCharacterPicture(imgView: ImageView, item: TvCharacter){
    item?.let{
        val imgUrl = BASE_URL + item.icon
        Glide.with(imgView.context)
            .load(imgUrl)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_foreground))
            .into(imgView)
    }
}

@BindingAdapter("detailTextViewName")
fun TextView.setDetailTextViewName(item: TvCharacter){
    item.let{
        text = item.name
    }
}
