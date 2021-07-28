package com.catnip.covidapp.data.network.datasource

import com.catnip.covidapp.data.network.entity.requests.authentification.LoginRequest
import com.catnip.covidapp.data.network.entity.requests.authentification.RegisterRequest
import com.catnip.covidapp.data.network.entity.responses.authentification.BaseAuthResponse
import com.catnip.covidapp.data.network.entity.responses.authentification.LoginResponse
import com.catnip.covidapp.data.network.entity.responses.authentification.UserResponse
import com.catnip.covidapp.data.network.services.BinarApiServices


/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class BinarDataSource(private val binarApiServices: BinarApiServices) {
    suspend fun postLoginData(loginRequest: LoginRequest) : BaseAuthResponse<LoginResponse, String>{
        return binarApiServices.postLoginData(loginRequest)
    }

    suspend fun postRegisterData(registerRequest: RegisterRequest) : BaseAuthResponse<UserResponse, String>{
        return binarApiServices.postRegisterData(registerRequest)

    }
    suspend fun getSyncData() : BaseAuthResponse<UserResponse, String>{
        return binarApiServices.getSyncData()
    }
}