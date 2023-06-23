package com.simform.coffeeshop.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("imageFromInt")
fun ImageView.imageFromInt(image: Int) {
    setImageResource(image)
}