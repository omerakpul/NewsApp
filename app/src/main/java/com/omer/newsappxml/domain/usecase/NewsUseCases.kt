package com.omer.newsappxml.domain.usecase

data class NewsUseCases(
    val getNews : GetNewsUseCase,
    val refreshNews : RefreshNewsUseCase,
    val searchNews : SearchNewsUseCase
)
