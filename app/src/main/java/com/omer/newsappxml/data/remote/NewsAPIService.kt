package com.omer.newsappxml.data.remote

import com.omer.newsappxml.BuildConfig
import com.omer.newsappxml.data.model.News
import com.omer.newsappxml.data.mapper.toRoomNewsList
import javax.inject.Inject


class NewsAPIService @Inject constructor(
    private val newsAPI: NewsAPI
) {
    suspend fun getDataByCategory(country: String, category: String): List<News>{
        val response = newsAPI.getNewsByCategory(country,category, BuildConfig.NEWS_API_KEY)
        return response.articles.toRoomNewsList(country,category)
    }
}