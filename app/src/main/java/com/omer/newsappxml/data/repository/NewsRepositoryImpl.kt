package com.omer.newsappxml.data.repository

import com.omer.newsappxml.data.local.NewsLocalDataSource
import com.omer.newsappxml.data.mapper.toDomain
import com.omer.newsappxml.data.mapper.toEntity
import com.omer.newsappxml.data.remote.NewsRemoteDataSource
import com.omer.newsappxml.domain.model.News
import com.omer.newsappxml.domain.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val localDataSource: NewsLocalDataSource,
    private val remoteDataSource: NewsRemoteDataSource
) : NewsRepository {

    override suspend fun getNews(country: String, category: String, fromInternet: Boolean): List<News> {
        val localEntities = localDataSource.getNews(country, category)
        if (fromInternet == true) {
            val remoteDtos = remoteDataSource.fetchNews(country, category)
            val entities = remoteDtos.map { it.toEntity(country, category) }
            localDataSource.deleteNews(country, category)
            localDataSource.insertNews(entities)
            return localDataSource.getNews(country, category).map { it.toDomain() }
        } else {
            if (localEntities.isNotEmpty()) {
                return localEntities.map { it.toDomain() }
            } else {
                val remoteDtos = remoteDataSource.fetchNews(country, category)
                val entities = remoteDtos.map { it.toEntity(country,category)}
                localDataSource.insertNews(entities)
                return localDataSource.getNews(country, category).map { it.toDomain() }
            }
        }
    }

    override suspend fun searchNews(query: String,country: String, category: String): List<News> {
        val allEntities = localDataSource.getNews(country,category)
        val filtered = allEntities.filter {
            it.title?.contains(query, true) == true ||
                    it.description?.contains(query, true) == true }
        return filtered.map { it.toDomain() }
    }


}