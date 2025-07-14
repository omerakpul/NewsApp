package com.omer.newsappxml.domain.usecase

import com.omer.newsappxml.domain.model.News
import com.omer.newsappxml.domain.repository.NewsRepository
import javax.inject.Inject

class SearchNewsUseCase @Inject constructor(
    private val repo : NewsRepository
) {
    suspend operator fun invoke(query: String,country: String, category: String): List<News> {
        return repo.searchNews(query,country, category)
    }
}