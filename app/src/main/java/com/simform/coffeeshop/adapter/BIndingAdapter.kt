package com.simform.coffeeshop.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageFromInt")
fun ImageView.imageFromInt(image: Int) {
    setImageResource(image)
}

@BindingAdapter("imageFromUrl")
fun ImageView.imageFromUrl(url: String) {
    Glide.with(this.context).load(url).into(this)
}