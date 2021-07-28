package com.catnip.covidapp.ui.register

import com.catnip.covidapp.data.network.datasource.BinarDataSource
import com.catnip.covidapp.data.network.entity.requests.authentification.RegisterRequest
import com.catnip.covidapp.data.network.entity.responses.authentification.BaseAuthResponse
import com.catnip.covidapp.data.network.entity.responses.authentification.UserResponse

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class RegisterRepository(private val binarDataSource: BinarDataSource) {
    suspend fun postRegisterData(registerRequest: RegisterRequest) : BaseAuthResponse<UserResponse, String> {
        return binarDataSource.postRegisterData(registerRequest)
    }
}