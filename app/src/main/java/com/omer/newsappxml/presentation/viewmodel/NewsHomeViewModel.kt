package com.omer.newsappxml.presentation.viewmodel

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
    private val newsUseCases : NewsUseCases
) : ViewModel() {

    val news = MutableLiveData<List<News>>()
    val newsError = MutableLiveData<Boolean>()
    val newsLoading = MutableLiveData<Boolean>()

    fun getNews(country:String, category: String){
        viewModelScope.launch {
            newsLoading.value = true
            try {
                news.value = newsUseCases.getNews(country, category)
                newsError.value = false
            } catch (e:Exception) {
                newsError.value = true
            } finally {
                newsLoading.value = false
            }
        }
    }

    fun refreshNews(country: String,category: String){
        viewModelScope.launch {
            newsLoading.value = true
            try {
                newsUseCases.refreshNews(country, category)
                news.value = newsUseCases.getNews(country, category)
                newsError.value = false
            } catch (e:Exception) {
                newsError.value = true
            } finally {
                newsLoading.value = false
            }
        }
    }

    fun searchNews(query:String,country: String, category: String) {
        viewModelScope.launch {
            newsLoading.value = true
            try {
                news.value = newsUseCases.searchNews(query,country, category)
                newsError.value = false
            } catch (e: Exception) {
                newsError.value = true
            } finally {
                newsLoading.value = false
            }
        }
    }
    }
