package com.catnip.covidapp.data.network.entity.responses.kawalcorona


import com.google.gson.annotations.SerializedName

data class TotalCaseProvinceResponse(
    @SerializedName("attributes")
    val attributes: Attributes? = null
)