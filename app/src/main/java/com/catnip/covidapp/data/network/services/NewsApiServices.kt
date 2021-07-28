package com.catnip.covidapp.data.network.services

import com.catnip.covidapp.BuildConfig
import com.catnip.covidapp.data.network.entity.responses.kawalcorona.TotalCaseProvinceResponse
import com.catnip.covidapp.data.network.entity.responses.kawalcorona.TotalCaseResponse
import com.catnip.covidapp.data.network.entity.responses.news.NewsReponse
import com.catnip.covidapp.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
interface NewsApiServices {

    @GET("search/")
    suspend fun getNews(
        @Query("q") query : String,
        @Query("api-key") apiKey : String = BuildConfig.API_KEY_GUARDIAN_NEWS,
        @Query("page-size") pageSize : Int = Constants.MAX_PER_PAGE,
        @Query("show-fields") showFields : String = Constants.ADDITIONAL_FIELDS,
        ) : NewsReponse

    companion object{
        private var retrofitServices : NewsApiServices? = null
        fun getInstance() : NewsApiServices?{
            if(retrofitServices == null){
                //initialize
                val okHttpClient = OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .connectTimeout(120, TimeUnit.SECONDS)
                    .readTimeout(120, TimeUnit.SECONDS)
                    .build()

                val retrofit = Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL_GUARDIAN_NEWS)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build()
                retrofitServices = retrofit.create(NewsApiServices::class.java)
            }
            return retrofitServices
        }
    }

}