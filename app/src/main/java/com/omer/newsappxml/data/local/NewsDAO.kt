package com.omer.newsappxml.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.omer.newsappxml.data.model.News

@Dao
interface NewsDAO {

    @Query("DELETE FROM News")
    suspend fun deleteAll()

    @Query("DELETE FROM News WHERE country = :country AND category = :category")
    suspend fun deleteFilteredNews(country: String, category: String)

    @Query("SELECT * FROM News")
    suspend fun getAllNews() : List<News>

    @Query("SELECT * FROM News WHERE country = :country AND category = :category")
    suspend fun getFilteredNews(country:String,category:String) : List<News>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(news: List<News>) : List<Long>



}