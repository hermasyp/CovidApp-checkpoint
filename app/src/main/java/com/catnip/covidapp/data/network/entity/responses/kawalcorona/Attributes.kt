package com.catnip.covidapp.data.network.entity.responses.kawalcorona


import com.google.gson.annotations.SerializedName

data class Attributes(
    @SerializedName("FID")
    val fID: Int? = null,
    @SerializedName("Kasus_Meni")
    val kasusMeni: Int? = null,
    @SerializedName("Kasus_Posi")
    val kasusPosi: Int? = null,
    @SerializedName("Kasus_Semb")
    val kasusSemb: Int? = null,
    @SerializedName("Kode_Provi")
    val kodeProvi: Int? = null,
    @SerializedName("Provinsi")
    val provinsi: String? = null
)