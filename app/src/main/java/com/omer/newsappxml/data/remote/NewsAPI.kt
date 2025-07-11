package com.omer.newsappxml.data.remote

import com.omer.newsappxml.data.model.NewsResponse
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