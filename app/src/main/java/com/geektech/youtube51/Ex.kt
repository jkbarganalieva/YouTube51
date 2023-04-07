package com.geektech.youtube51

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(image: String) {
    Glide.with(this).load(image).into(this)
}