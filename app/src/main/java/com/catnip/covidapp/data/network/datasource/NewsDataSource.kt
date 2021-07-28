package com.catnip.covidapp.data.network.datasource

import com.catnip.covidapp.BuildConfig
import com.catnip.covidapp.data.network.entity.responses.news.NewsReponse
import com.catnip.covidapp.data.network.services.NewsApiServices
import com.catnip.covidapp.utils.Constants
import retrofit2.http.Query

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class NewsDataSource(private val newsApiServices: NewsApiServices) {
    suspend fun getNews(query : String) : NewsReponse{
        return newsApiServices.getNews(query)
    }
}