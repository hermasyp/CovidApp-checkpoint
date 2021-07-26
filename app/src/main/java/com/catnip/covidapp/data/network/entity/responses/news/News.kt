package com.catnip.covidapp.data.network.entity.responses.news


import com.google.gson.annotations.SerializedName

data class News(
    @SerializedName("apiUrl")
    val apiUrl: String? = null,
    @SerializedName("fields")
    val fields: Fields? = null,
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("isHosted")
    val isHosted: Boolean? = null,
    @SerializedName("pillarId")
    val pillarId: String? = null,
    @SerializedName("pillarName")
    val pillarName: String? = null,
    @SerializedName("sectionId")
    val sectionId: String? = null,
    @SerializedName("sectionName")
    val sectionName: String? = null,
    @SerializedName("type")
    val type: String? = null,
    @SerializedName("webPublicationDate")
    val webPublicationDate: String? = null,
    @SerializedName("webTitle")
    val webTitle: String? = null,
    @SerializedName("webUrl")
    val webUrl: String? = null
)