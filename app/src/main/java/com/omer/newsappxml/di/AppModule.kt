package com.omer.newsappxml.di

import android.content.Context
import androidx.room.Room
import com.omer.newsappxml.BuildConfig
import com.omer.newsappxml.roomdb.NewsDAO
import com.omer.newsappxml.roomdb.NewsDatabase
import com.omer.newsappxml.service.NewsAPI
import com.omer.newsappxml.service.NewsAPIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.NEWS_API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsAPI(retrofit: Retrofit) : NewsAPI {
        return retrofit.create(NewsAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsDatabase(
        @ApplicationContext appContext: Context
    ): NewsDatabase {
        return Room.databaseBuilder(
            appContext,
            NewsDatabase::class.java,
            "news_db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDAO(newsDatabase: NewsDatabase) : NewsDAO {
        return newsDatabase.newsDao()
    }



}