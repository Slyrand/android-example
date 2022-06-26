package com.slyrand.mvvmapp.core.extensions

import androidx.appcompat.widget.SearchView
import java.util.Timer
import kotlin.concurrent.schedule

fun SearchView.setOnQueryChangedListener(func: (String) -> Unit) {
    setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        var debounceHandler = Timer()

        override fun onQueryTextSubmit(p0: String?): Boolean {
            func(p0.toString())
            return true
        }

        override fun onQueryTextChange(p0: String?): Boolean {
            debounceHandler.cancel()
            debounceHandler = Timer()
            debounceHandler.schedule(400L) { func(p0.toString()) }
            return true
        }
    })
}