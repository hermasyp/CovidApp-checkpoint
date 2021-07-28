package com.catnip.covidapp.data.network.services

import com.catnip.covidapp.BuildConfig
import com.catnip.covidapp.data.network.entity.responses.kawalcorona.TotalCaseProvinceResponse
import com.catnip.covidapp.data.network.entity.responses.kawalcorona.TotalCaseResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
interface CovidApiServices {

    @GET("indonesia/")
    suspend fun getConfirmedCaseTotal() : List<TotalCaseResponse>

    @GET("indonesia/provinsi/")
    suspend fun getConfirmedCaseByProvice() : List<TotalCaseProvinceResponse>

    companion object{
        private var retrofitServices : CovidApiServices? = null
        fun getInstance() : CovidApiServices?{
            if(retrofitServices == null){
                //initialize
                val okHttpClient = OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .connectTimeout(120, TimeUnit.SECONDS)
                    .readTimeout(120, TimeUnit.SECONDS)
                    .build()

                val retrofit = Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL_KAWAL_CORONA)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build()
                retrofitServices = retrofit.create(CovidApiServices::class.java)
            }
            return retrofitServices
        }
    }

}