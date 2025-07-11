package com.omer.newsappxml.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.omer.newsappxml.model.News

@Database(entities = [News::class], version = 5, exportSchema = false)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDAO
}