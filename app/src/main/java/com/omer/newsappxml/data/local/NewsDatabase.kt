package com.omer.newsappxml.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.omer.newsappxml.data.model.NewsEntity

@Database(entities = [NewsEntity::class], version = 6, exportSchema = false)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDAO
}