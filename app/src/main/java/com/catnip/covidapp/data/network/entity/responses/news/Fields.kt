package com.catnip.covidapp.data.network.entity.responses.news


import com.google.gson.annotations.SerializedName

data class Fields(
    @SerializedName("thumbnail")
    val thumbnail: String? = null,
    @SerializedName("trailText")
    val trailText: String? = null
)