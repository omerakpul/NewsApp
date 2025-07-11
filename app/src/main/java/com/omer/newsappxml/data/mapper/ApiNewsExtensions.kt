package com.omer.newsappxml.data.mapper

import com.omer.newsappxml.data.remote.dto.ApiNewsDto
import com.omer.newsappxml.data.model.News

fun ApiNewsDto.toRoomNews(country: String, category: String): News = News(
    url = this.url,
    author = this.author,
    title = this.title,
    description = this.description,
    urlToImage = this.urlToImage,
    content = this.content,
    country = country,
    category = category
)

fun List<ApiNewsDto>.toRoomNewsList(country: String, category: String): List<News> = this.map { it.toRoomNews(country, category) }