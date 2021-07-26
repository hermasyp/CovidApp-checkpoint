package com.catnip.covidapp.data.network.entity.responses.authentification


import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("_id")
    val id: String? = null,
    @SerializedName("username")
    val username: String? = null
)