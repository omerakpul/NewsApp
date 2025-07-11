package com.omer.newsappxml.model

data class NewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<ApiNews>
)