package com.omer.newsappxml.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omer.newsappxml.domain.model.News
import com.omer.newsappxml.domain.usecase.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NewsHomeViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases,
) : ViewModel() {

    private val _newsState = MutableLiveData<NewsUiState>()
    val newsState: LiveData<NewsUiState> = _newsState

    fun getNews(country: String, category: String, fromInternet: Boolean) {
        viewModelScope.launch {
            _newsState.value = NewsUiState.Loading
            try {
                val result = newsUseCases.getNews(country, category, fromInternet)
                _newsState.value = NewsUiState.Success(result)
            } catch (e: Exception) {
                _newsState.value = NewsUiState.Error(e.message)
            }
        }
    }


    fun searchNews(query: String, country: String, category: String) {
        viewModelScope.launch {
            _newsState.value = NewsUiState.Loading
            try {
                val result = newsUseCases.searchNews(query, country, category)
                _newsState.value = NewsUiState.Success(result)
            } catch (e: Exception) {
                _newsState.value = NewsUiState.Error(e.message)
            }
        }
    }
}
