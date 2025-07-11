package com.omer.newsappxml.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omer.newsappxml.model.News
import com.omer.newsappxml.roomdb.NewsDAO
import com.omer.newsappxml.roomdb.NewsDatabase
import com.omer.newsappxml.service.NewsAPI
import com.omer.newsappxml.service.NewsAPIService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class NewsHomeViewModel @Inject constructor(
    private val newsAPIService: NewsAPIService,
    private val newsDao: NewsDAO,
) : ViewModel() {
    val news = MutableLiveData<List<News>>()
    val newsErrorMessage = MutableLiveData<Boolean>()
    val newsLoading = MutableLiveData<Boolean>()
    private var allNews: List<News> = listOf()

    fun takeDataFromRoom() {
        newsLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val newsList = newsDao.getAllNews()
            withContext(Dispatchers.Main) {
                showNews(newsList)
            }
        }
    }

    fun takeDataFromInternet(country: String, category: String) {
        newsLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val newsList = newsAPIService.getDataByCategory(country,category)
                newsDao.deleteFilteredNews(country,category)
                newsDao.insertAll(newsList)
                val updatedNews = newsDao.getFilteredNews(country,category)
                withContext(Dispatchers.Main) {
                    showNews(updatedNews)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    newsErrorMessage.value = true
                    newsLoading.value = false
                }
            }
        }
    }

    private fun showNews(newsList: List<News>) {
        allNews = newsList
        news.value = newsList
        newsErrorMessage.value = false
        newsLoading.value = false
    }

    fun searchNews(query: String) {
        val filteredNews = allNews.filter { news ->
            news.title?.contains(query, ignoreCase = true) == true ||
                    news.description?.contains(query, ignoreCase = true) == true
            }
        news.value = filteredNews
    }

    fun getNewsRoomOrInternet(country: String,category: String){
    newsLoading.value = true
        viewModelScope.launch(Dispatchers.IO){
            val newsList = newsDao.getFilteredNews(country, category)
            if(newsList.isNotEmpty()){
                withContext(Dispatchers.Main){
                    showNews(newsList)
                }
            } else {
                try {
                    val apiNews = newsAPIService.getDataByCategory(country,category)
                    newsDao.deleteFilteredNews(country,category)
                    newsDao.insertAll(apiNews)
                    val updatedNews = newsDao.getFilteredNews(country,category)
                    withContext(Dispatchers.Main) {
                        showNews(updatedNews)
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        newsErrorMessage.value = true
                        newsLoading.value = false
                    }
                }
            }
        }
    }
    }
