package com.omer.newsappxml.data.remote

import com.omer.newsappxml.data.remote.dto.ApiNewsDto
import javax.inject.Inject

class NewsRemoteDataSource @Inject constructor(
    private val apiService: NewsAPIService
) {
    suspend fun fetchNews(country: String, category: String): List<ApiNewsDto> =
        apiService.getDataByCategory(country, category)
}