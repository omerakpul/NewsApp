package com.omer.newsappxml.domain.model

import java.io.Serializable

data class News (
    val url: String,
    val author: String?,
    val title: String?,
    val description: String?,
    val urlToImage: String?,
    val content: String?,
    val category: String?,
    val country: String?
) : Serializable