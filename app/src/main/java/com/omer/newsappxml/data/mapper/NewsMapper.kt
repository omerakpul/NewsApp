package com.omer.newsappxml.data.mapper

import com.omer.newsappxml.domain.model.News
import com.omer.newsappxml.data.model.News as NewsEntity

class NewsMapper {

    fun NewsEntity.toDomain(): News = News(
        url = url,
        author = author,
        title = title,
        description = description,
        urlToImage = urlToImage,
        content = content,
        category = category,
        country = country
    )

    fun News.toEntity(): NewsEntity = NewsEntity (
        url = url,
        author = author,
        title = title,
        description = description,
        urlToImage = urlToImage,
        content = content,
        category = category,
        country = country
    )

}