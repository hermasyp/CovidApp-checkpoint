package com.catnip.covidapp.ui.login

import com.catnip.covidapp.data.network.datasource.BinarDataSource
import com.catnip.covidapp.data.network.entity.requests.authentification.LoginRequest
import com.catnip.covidapp.data.network.entity.requests.authentification.RegisterRequest
import com.catnip.covidapp.data.network.entity.responses.authentification.BaseAuthResponse
import com.catnip.covidapp.data.network.entity.responses.authentification.LoginResponse
import com.catnip.covidapp.data.network.entity.responses.authentification.UserResponse

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class LoginRepository(private val binarDataSource: BinarDataSource) {
    suspend fun postLoginData(loginRequest: LoginRequest) : BaseAuthResponse<LoginResponse, String>{
        return binarDataSource.postLoginData(loginRequest)
    }
}