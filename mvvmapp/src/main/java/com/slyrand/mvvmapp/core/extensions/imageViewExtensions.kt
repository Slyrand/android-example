package com.slyrand.mvvmapp.core.extensions

import androidx.annotation.ColorRes
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide

fun AppCompatImageView.loadImage(url: String?) {
    Glide.with(context).load(url).into(this)
}

fun AppCompatImageView.applyTint(@ColorRes colorRes: Int) {
    setColorFilter(color(colorRes))
}