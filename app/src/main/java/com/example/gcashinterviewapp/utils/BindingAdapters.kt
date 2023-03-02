package com.example.gcashinterviewapp.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.gcashinterviewapp.R


@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String?) {
        Glide.with(imageView.context).load(Constants.IMAGE_URL + url + ".png").placeholder(R.drawable.ic_no_img)
            .into(imageView)
}


