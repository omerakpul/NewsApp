package com.omer.newsappxml.domain.repository

import com.omer.newsappxml.domain.model.News

interface NewsRepository {
    suspend fun getNews(country: String, category: String): List<News>
    suspend fun refreshNews(country: String, category: String)
    suspend fun searchNews(query: String): List<News>
}