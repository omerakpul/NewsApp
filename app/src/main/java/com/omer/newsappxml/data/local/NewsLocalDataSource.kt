package com.omer.newsappxml.data.local

import com.omer.newsappxml.data.model.NewsEntity as NewsEntity
import javax.inject.Inject

class NewsLocalDataSource @Inject constructor(
    private val newsDAO: NewsDAO
) {

    suspend fun getNews(country: String, category: String): List<NewsEntity> =
        newsDAO.getFilteredNews(country, category)

    suspend fun deleteNews(country: String, category: String) =
        newsDAO.deleteFilteredNews(country, category)

    suspend fun insertNews(news: List<NewsEntity>) =
        newsDAO.insertAll(news)

    suspend fun getAllNews() : List<NewsEntity> =
        newsDAO.getAllNews()
}