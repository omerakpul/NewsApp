package com.omer.newsappxml.data.mapper

import com.omer.newsappxml.data.remote.dto.ApiNewsDto
import com.omer.newsappxml.domain.model.News
import com.omer.newsappxml.data.model.News as NewsEntity

    fun NewsEntity.toDomain(): News = News(
        title = title ?: "No title",
        description = description ?: "",
        urlToImage = urlToImage ?: "",
        category = category ?: "",
        country = country ?: ""
    )

    fun ApiNewsDto.toEntity(country: String, category: String): NewsEntity {
        return NewsEntity(
            url = this.url ?: "",
            author = this.author,
            title = this.title,
            description = this.description,
            urlToImage = this.urlToImage,
            content = this.content,
            category = category,
            country = country
        )
    }