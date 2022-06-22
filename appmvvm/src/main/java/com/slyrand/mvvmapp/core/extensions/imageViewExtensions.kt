package com.slyrand.mvvmapp.core.extensions

import androidx.annotation.ColorRes
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

fun AppCompatImageView.loadThumbnail(url: String?) {
    val requestOptions = RequestOptions()
        .transform(
            CenterCrop(),
            RoundedCorners(20)
        )
    Glide.with(context)
        .load(url)
        .apply(requestOptions)
        .into(this)
}

fun AppCompatImageView.applyTint(@ColorRes colorRes: Int) {
    setColorFilter(color(colorRes))
}