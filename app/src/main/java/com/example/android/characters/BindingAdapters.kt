package com.example.android.characters

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class BindingAdapters {
    @BindingAdapter("picOfTheDay")
    fun bindPicOfTheDay(imgView: ImageView, imgUrl: String?){
        imgUrl?.let{
            val imgUrl = imgUrl.toUri().buildUpon().scheme("https").build()
//            Glide.with(imgView.context)
//                .load(imgUrl)
//                .apply(
//                    RequestOptions()
//                    .placeholder(R.drawable.placeholder_picture_of_day)
//                    .error(R.drawable.ic_help_circle))
//                .into(imgView)

        }
    }
}