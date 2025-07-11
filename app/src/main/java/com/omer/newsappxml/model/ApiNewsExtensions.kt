package com.omer.newsappxml.model

fun ApiNews.toRoomNews(country: String, category: String): News = News(
    url = this.url,
    author = this.author,
    title = this.title,
    description = this.description,
    urlToImage = this.urlToImage,
    content = this.content,
    country = country,
    category = category
)

fun List<ApiNews>.toRoomNewsList(country: String, category: String): List<News> = this.map { it.toRoomNews(country, category) }