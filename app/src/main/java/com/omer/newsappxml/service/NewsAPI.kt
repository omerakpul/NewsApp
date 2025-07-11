package com.omer.newsappxml.service

import com.omer.newsappxml.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {

    @GET("top-headlines")
    suspend fun getNewsByCategory(
        @Query("country") country: String,
        @Query("category") category: String,
        @Query("apiKey") apiKey: String
    ): NewsResponse

}