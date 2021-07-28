package com.catnip.covidapp.ui.home.covidspread

import com.catnip.covidapp.data.network.datasource.CovidDataSource
import com.catnip.covidapp.data.network.entity.responses.kawalcorona.TotalCaseProvinceResponse
import com.catnip.covidapp.data.network.entity.responses.kawalcorona.TotalCaseResponse

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class CovidSpreadRepository(private val dataSource: CovidDataSource) {

    suspend fun getCovidSpreadData(): Pair<TotalCaseResponse, List<TotalCaseProvinceResponse>> {
        return Pair(
            dataSource.getConfirmedCaseTotal()[0],
            dataSource.getConfirmedCaseByProvice()
        )
    }
}