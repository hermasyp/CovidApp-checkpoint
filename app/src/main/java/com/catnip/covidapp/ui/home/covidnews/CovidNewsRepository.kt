package com.catnip.covidapp.ui.home.covidnews

import com.catnip.covidapp.data.network.datasource.NewsDataSource
import com.catnip.covidapp.data.network.entity.responses.news.NewsReponse

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class CovidNewsRepository(private val dataSource: NewsDataSource) {
    suspend fun getNews(query: String): NewsReponse {
        return dataSource.getNews(query)
    }
}