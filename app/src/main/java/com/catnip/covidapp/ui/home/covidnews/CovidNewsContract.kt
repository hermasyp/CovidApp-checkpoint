package com.catnip.covidapp.ui.home.covidnews

import com.catnip.covidapp.base.BaseContract
import com.catnip.covidapp.data.network.entity.responses.kawalcorona.TotalCaseProvinceResponse
import com.catnip.covidapp.data.network.entity.responses.kawalcorona.TotalCaseResponse
import com.catnip.covidapp.data.network.entity.responses.news.News

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
interface CovidNewsContract {
    interface ViewModel : BaseContract.ViewModel{
        fun getNews()
    }
    interface View : BaseContract.View{
        fun showContent(isContentVisible : Boolean)
        fun showLoading(isLoading : Boolean)
        fun showError(isErrorEnabled : Boolean,msg : String?)
        fun setupSwipeRefresh()
        fun setupList()
        fun setListData(data : List<News>)
    }
}