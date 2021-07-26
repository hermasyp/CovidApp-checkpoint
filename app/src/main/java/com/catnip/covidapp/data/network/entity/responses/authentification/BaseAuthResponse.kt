package com.catnip.covidapp.data.network.entity.responses.authentification

import com.google.gson.annotations.SerializedName

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
data class BaseAuthResponse<T,E>(
    @SerializedName("success")
    private val success : Boolean,
    @SerializedName("data")
    private val data : T,
    @SerializedName("errors")
    private val errors : E
)
// LoginResponse
// BaseAuthResponse<LoginResponse,String>
// RegisterResponse
// BaseAuthResponse<RegisterResponse,String>