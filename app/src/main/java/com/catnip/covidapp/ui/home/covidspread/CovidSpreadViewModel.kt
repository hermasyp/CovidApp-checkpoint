package com.catnip.covidapp.ui.home.covidspread

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.catnip.covidapp.base.Resource
import com.catnip.covidapp.data.network.entity.responses.kawalcorona.TotalCaseProvinceResponse
import com.catnip.covidapp.data.network.entity.responses.kawalcorona.TotalCaseResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class CovidSpreadViewModel(private val repository: CovidSpreadRepository) : ViewModel(),
    CovidSpreadContract.ViewModel {

    val covidData =
        MutableLiveData<Resource<Pair<TotalCaseResponse, List<TotalCaseProvinceResponse>>>>()

    override fun getCovidSpreadData() {
        covidData.value = Resource.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getCovidSpreadData()
                viewModelScope.launch(Dispatchers.Main) {
                    covidData.value = Resource.Success(response)
                }
            }catch (e : Exception){
                viewModelScope.launch(Dispatchers.Main) {
                    covidData.value = Resource.Error(e.message.orEmpty())
                }
            }
        }
    }

}