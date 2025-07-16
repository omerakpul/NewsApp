package com.omer.newsappxml.domain.repository

import com.omer.newsappxml.domain.model.News

interface NewsRepository {
    suspend fun getNews(country: String, category: String, fromInternet: Boolean = false): List<News>
    suspend fun searchNews(query: String,country: String, category: String): List<News>
}