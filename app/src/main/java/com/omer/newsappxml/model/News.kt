package com.omer.newsappxml.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class News(
    @PrimaryKey
    val url: String,
    val author: String?,
    val title: String?,
    val description: String?,
    val urlToImage: String?,
    val content: String?,
    val category: String?,
    val country: String?
) : Serializable