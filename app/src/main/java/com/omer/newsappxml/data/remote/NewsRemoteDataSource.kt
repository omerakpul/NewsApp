package com.omer.newsappxml.data.remote

import com.omer.newsappxml.BuildConfig
import com.omer.newsappxml.data.remote.dto.ApiNewsDto
import javax.inject.Inject

class NewsRemoteDataSource @Inject constructor(
    private val apiService: NewsAPIService
) {
    suspend fun fetchNews(country: String, category: String): List<ApiNewsDto> {
        val response = apiService.getNewsByCategory(country, category, BuildConfig.NEWS_API_KEY)
        return response.articles
    }
}