package com.omer.newsappxml.util

import android.view.View
import androidx.appcompat.widget.SearchView


fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun SearchView.clear(){
    this.setQuery("", false)
    this.clearFocus()
}

