package com.catnip.covidapp.data.network.datasource

import com.catnip.covidapp.data.network.entity.responses.kawalcorona.TotalCaseProvinceResponse
import com.catnip.covidapp.data.network.entity.responses.kawalcorona.TotalCaseResponse
import com.catnip.covidapp.data.network.services.CovidApiServices

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class CovidDataSource(private val covidApiServices: CovidApiServices) {
    suspend fun getConfirmedCaseTotal() : List<TotalCaseResponse>{
        return covidApiServices.getConfirmedCaseTotal()
    }
    suspend fun getConfirmedCaseByProvice() : List<TotalCaseProvinceResponse>{
        return covidApiServices.getConfirmedCaseByProvice()
    }
}