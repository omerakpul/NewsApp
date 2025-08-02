package com.omer.newsappxml.data.remote.dto

data class NewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<ApiNewsDto>
)