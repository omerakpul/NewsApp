package com.omer.newsappxml.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.omer.newsappxml.data.model.News

@Database(entities = [News::class], version = 5, exportSchema = false)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDAO
}