package com.omer.newsappxml.data.remote

import com.omer.newsappxml.BuildConfig
import com.omer.newsappxml.data.remote.dto.ApiNewsDto
import javax.inject.Inject


class NewsAPIService @Inject constructor(
    private val newsAPI: NewsAPI
) {
    suspend fun getDataByCategory(country: String, category: String): List<ApiNewsDto>{
        val response = newsAPI.getNewsByCategory(country,category, BuildConfig.NEWS_API_KEY)
        return response.articles
    }
}