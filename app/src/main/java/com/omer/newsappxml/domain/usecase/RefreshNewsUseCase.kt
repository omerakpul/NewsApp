package com.omer.newsappxml.domain.usecase

import com.omer.newsappxml.domain.repository.NewsRepository

class RefreshNewsUseCase(private val repo : NewsRepository) {

    suspend operator fun invoke(country:String, category:String) {
        repo.refreshNews(country, category)
    }
}