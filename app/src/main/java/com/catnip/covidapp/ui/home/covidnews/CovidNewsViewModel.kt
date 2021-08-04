package com.catnip.covidapp.ui.home.covidnews

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.catnip.covidapp.base.Resource
import com.catnip.covidapp.data.network.entity.responses.news.News
import com.catnip.covidapp.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.URLEncoder

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class CovidNewsViewModel(private val repository: CovidNewsRepository) : ViewModel(),
    CovidNewsContract.ViewModel {

    val newsData = MutableLiveData<Resource<List<News>?>>()

    override fun getNews() {
        newsData.value = Resource.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getNews(URLEncoder.encode(Constants.QUERY_NEWS_COVID_INDONESIA,"UTF-8"))
                viewModelScope.launch(Dispatchers.Main) {
                    newsData.value = Resource.Success(response.response?.news)
                }
            } catch (e: Exception) {
                viewModelScope.launch(Dispatchers.Main) {
                    newsData.value = Resource.Error(e.message.orEmpty())
                }
            }
        }
    }

}