package com.omer.newsappxml.domain.usecase

import com.omer.newsappxml.domain.model.News
import com.omer.newsappxml.domain.repository.NewsRepository

class GetNewsUseCase(private val repo : NewsRepository) {

    suspend operator fun invoke(country:String, category:String) : List<News> {
        return repo.getNews(country,category)
    }

}