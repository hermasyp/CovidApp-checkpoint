package com.catnip.covidapp.data.network.services

import com.catnip.covidapp.BuildConfig
import com.catnip.covidapp.data.local.sharedpreference.SessionPreference
import com.catnip.covidapp.data.network.entity.requests.authentification.LoginRequest
import com.catnip.covidapp.data.network.entity.requests.authentification.RegisterRequest
import com.catnip.covidapp.data.network.entity.responses.authentification.BaseAuthResponse
import com.catnip.covidapp.data.network.entity.responses.authentification.LoginResponse
import com.catnip.covidapp.data.network.entity.responses.authentification.UserResponse
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
interface BinarApiServices {

    @POST("api/v1/auth/login")
    suspend fun postLoginData(@Body loginRequest: LoginRequest): BaseAuthResponse<LoginResponse, String>

    @POST("api/v1/auth/register")
    suspend fun postRegisterData(@Body registerRequest: RegisterRequest): BaseAuthResponse<UserResponse, String>

    @GET("api/v1/auth/me")
    suspend fun getSyncData(): BaseAuthResponse<UserResponse, String>

    @GET("/api/v1/users")
    suspend fun getProfileData(): BaseAuthResponse<UserResponse, String>

    @PUT("/api/v1/users")
    suspend fun putProfileData(
        @Body data: RequestBody
    ): BaseAuthResponse<UserResponse, String>


    companion object {
        private var retrofitServices: BinarApiServices? = null
        fun getInstance(sessionPreference: SessionPreference): BinarApiServices? {
            if (retrofitServices == null) {
                //todo : add interceptor token
                val authInterceptor = Interceptor {
                    val requestBuilder = it.request().newBuilder()
                    sessionPreference.authToken?.let { token ->
                        requestBuilder.addHeader("Authorization", "Bearer $token")
                    }
                    it.proceed(requestBuilder.build())
                }

                //initialize
                val okHttpClient = OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .addInterceptor(authInterceptor)
                    .connectTimeout(120, TimeUnit.SECONDS)
                    .readTimeout(120, TimeUnit.SECONDS)
                    .build()

                val retrofit = Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL_BINAR_AUTH)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build()
                retrofitServices = retrofit.create(BinarApiServices::class.java)
            }
            return retrofitServices
        }
    }

}