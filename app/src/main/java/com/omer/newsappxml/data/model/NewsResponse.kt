package com.omer.newsappxml.data.model

import com.omer.newsappxml.data.remote.dto.ApiNewsDto

data class NewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<ApiNewsDto>
)