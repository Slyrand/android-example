package com.slyrand.mvvmapp.core.extensions

import android.view.View
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

fun View.color(@ColorRes colorRes: Int): Int {
    return ContextCompat.getColor(context, colorRes)
}