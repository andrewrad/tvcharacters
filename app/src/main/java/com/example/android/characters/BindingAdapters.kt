package com.example.android.characters

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.android.characters.api.BASE_URL
import com.example.android.characters.ui.TvCharacterUiModel


//Recycler View list item binding:
@BindingAdapter("textViewName")
fun TextView.setTextViewName(item: TvCharacterUiModel?) {
    item?.let {
        text = item.name
    }
}

//DetailFragment binding view for imageview
@BindingAdapter("characterPicture")
fun bindCharacterPicture(imgView: ImageView, item: TvCharacterUiModel) {
    val imgUrl = BASE_URL + item.icon
    Glide.with(imgView.context)
        .load(imgUrl)
        .apply(
            RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
        )
        .into(imgView)
}

//DetailFragment binding view for the textview showing name
@BindingAdapter("detailTextViewName")
fun TextView.setDetailTextViewName(item: TvCharacterUiModel) {
    text = item.name
}

//DetailFragment binding view for the textview showing block of text
@BindingAdapter("detailTextViewText")
fun TextView.setDetailTextViewText(item: TvCharacterUiModel) {
    text = item.text
}