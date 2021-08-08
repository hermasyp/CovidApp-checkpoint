package com.catnip.covidapp.data.network.datasource

import com.catnip.covidapp.data.network.entity.requests.authentification.LoginRequest
import com.catnip.covidapp.data.network.entity.requests.authentification.RegisterRequest
import com.catnip.covidapp.data.network.entity.responses.authentification.BaseAuthResponse
import com.catnip.covidapp.data.network.entity.responses.authentification.LoginResponse
import com.catnip.covidapp.data.network.entity.responses.authentification.UserResponse
import com.catnip.covidapp.data.network.services.BinarApiServices
import okhttp3.MultipartBody


/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class BinarDataSource(private val binarApiServices: BinarApiServices) {
    suspend fun postLoginData(loginRequest: LoginRequest): BaseAuthResponse<LoginResponse, String> {
        return binarApiServices.postLoginData(loginRequest)
    }

    suspend fun postRegisterData(registerRequest: RegisterRequest): BaseAuthResponse<UserResponse, String> {
        return binarApiServices.postRegisterData(registerRequest)

    }

    suspend fun getSyncData(): BaseAuthResponse<UserResponse, String> {
        return binarApiServices.getSyncData()
    }

    suspend fun getProfileData(): BaseAuthResponse<UserResponse, String> {
        return binarApiServices.getProfileData()
    }

    suspend fun putProfileData(
        email: String,
        username: String
    ): BaseAuthResponse<UserResponse, String> {
        val requestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("email", email)
            .addFormDataPart("username", username)
            .build()
        return binarApiServices.putProfileData(requestBody)
    }
}