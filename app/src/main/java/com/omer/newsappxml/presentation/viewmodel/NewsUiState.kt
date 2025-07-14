package com.omer.newsappxml.presentation.viewmodel

import com.omer.newsappxml.domain.model.News

sealed class NewsUiState {
    object Loading : NewsUiState()
    data class Success(val news: List<News>) : NewsUiState()
    data class Error(val message : String? = null) : NewsUiState()
}