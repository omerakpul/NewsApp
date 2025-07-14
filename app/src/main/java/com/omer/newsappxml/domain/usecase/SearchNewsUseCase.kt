package com.omer.newsappxml.domain.usecase

import com.omer.newsappxml.domain.model.News
import com.omer.newsappxml.domain.repository.NewsRepository

class SearchNewsUseCase(private val repo : NewsRepository) {
    suspend operator fun invoke(query: String) : List<News> {
        return repo.searchNews(query)
    }

}