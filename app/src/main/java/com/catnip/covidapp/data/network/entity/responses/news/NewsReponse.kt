package com.catnip.covidapp.data.network.entity.responses.news


import com.google.gson.annotations.SerializedName

data class NewsReponse(
    @SerializedName("response")
    val response: Response? = null
)