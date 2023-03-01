package com.example.gcashinterviewapp.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String?) {
    url?.let {
        Glide.with(imageView.context).load(Constants.IMAGE_URL  + url +".png").into(imageView)
    }
}


