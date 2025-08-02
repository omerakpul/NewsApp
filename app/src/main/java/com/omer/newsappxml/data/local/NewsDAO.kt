package com.omer.newsappxml.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.omer.newsappxml.data.model.NewsEntity

@Dao
interface NewsDAO {

    @Query("DELETE FROM NewsEntity")
    suspend fun deleteAll()

    @Query("DELETE FROM NewsEntity WHERE country = :country AND category = :category")
    suspend fun deleteFilteredNews(country: String, category: String)

    @Query("SELECT * FROM NewsEntity")
    suspend fun getAllNews() : List<NewsEntity>

    @Query("SELECT * FROM NewsEntity WHERE country = :country AND category = :category")
    suspend fun getFilteredNews(country:String,category:String) : List<NewsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(newsEntities: List<NewsEntity>) : List<Long>
}