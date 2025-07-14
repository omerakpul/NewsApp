package com.omer.newsappxml.di

import com.omer.newsappxml.data.repository.NewsRepositoryImpl
import com.omer.newsappxml.domain.repository.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindNewsRepository(
        newsRepositoryImpl: NewsRepositoryImpl
    ) : NewsRepository
}