package com.catnip.covidapp.data.network.entity.requests.authentification

import com.google.gson.annotations.SerializedName

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
data class RegisterRequest(
    @SerializedName("email")
    private var email : String,
    @SerializedName("username")
    private var username : String,
    @SerializedName("password")
    private var password : String
)
