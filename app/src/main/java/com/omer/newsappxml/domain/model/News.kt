package com.omer.newsappxml.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class News (
    val title: String,
    val description: String,
    val urlToImage: String,
    val category: String,
    val country: String
) : Parcelable