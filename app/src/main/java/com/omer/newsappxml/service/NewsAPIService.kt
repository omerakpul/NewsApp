package com.omer.newsappxml.service

import com.omer.newsappxml.BuildConfig
import com.omer.newsappxml.model.News
import com.omer.newsappxml.model.toRoomNewsList
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject


class NewsAPIService @Inject constructor(
    private val newsAPI: NewsAPI
) {
    suspend fun getDataByCategory(country: String, category: String): List<News>{
        val response = newsAPI.getNewsByCategory(country,category, BuildConfig.NEWS_API_KEY)
        return response.articles.toRoomNewsList(country,category)
    }
}