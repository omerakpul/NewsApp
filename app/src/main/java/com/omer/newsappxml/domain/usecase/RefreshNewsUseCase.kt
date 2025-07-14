package com.omer.newsappxml.domain.usecase

import com.omer.newsappxml.domain.repository.NewsRepository
import javax.inject.Inject

class RefreshNewsUseCase @Inject constructor(
    private val repo : NewsRepository
) {
    suspend operator fun invoke(country:String, category:String) {
        repo.refreshNews(country, category)
    }
}