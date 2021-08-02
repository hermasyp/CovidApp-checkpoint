package com.catnip.covidapp.ui.home.covidspread

import com.catnip.covidapp.base.BaseContract
import com.catnip.covidapp.data.network.entity.responses.kawalcorona.TotalCaseProvinceResponse
import com.catnip.covidapp.data.network.entity.responses.kawalcorona.TotalCaseResponse

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
interface CovidSpreadContract {
    interface ViewModel : BaseContract.ViewModel{
        fun getCovidSpreadData()
    }
    interface View : BaseContract.View{
        fun showContent(isContentVisible : Boolean)
        fun showLoading(isLoading : Boolean)
        fun showError(isErrorEnabled : Boolean,msg : String?)
        fun setupSwipeRefresh()
        fun setupList()
        fun setListData(data : List<TotalCaseProvinceResponse>)
        fun bindDataHeader(data : TotalCaseResponse)
    }
}