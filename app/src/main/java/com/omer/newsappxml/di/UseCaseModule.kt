package com.omer.newsappxml.di

import com.omer.newsappxml.domain.repository.NewsRepository
import com.omer.newsappxml.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    fun provideNewsUseCase(
        newsRepository: NewsRepository
    ) : NewsUseCases {
        return NewsUseCases(
            getNews = GetNewsUseCase(newsRepository),
            refreshNews = RefreshNewsUseCase(newsRepository),
            searchNews = SearchNewsUseCase(newsRepository)
        )
    }
}