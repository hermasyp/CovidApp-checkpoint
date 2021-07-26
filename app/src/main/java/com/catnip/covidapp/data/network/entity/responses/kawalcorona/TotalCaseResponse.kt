package com.catnip.covidapp.data.network.entity.responses.kawalcorona


import com.google.gson.annotations.SerializedName

data class TotalCaseResponse(
    @SerializedName("dirawat")
    val dirawat: String? = null,
    @SerializedName("meninggal")
    val meninggal: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("positif")
    val positif: String? = null,
    @SerializedName("sembuh")
    val sembuh: String? = null
)