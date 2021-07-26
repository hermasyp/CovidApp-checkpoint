package com.catnip.covidapp.data.network.entity.responses.news


import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("currentPage")
    val currentPage: Int? = null,
    @SerializedName("orderBy")
    val orderBy: String? = null,
    @SerializedName("pageSize")
    val pageSize: Int? = null,
    @SerializedName("pages")
    val pages: Int? = null,
    @SerializedName("results")
    val news: List<News>? = null,
    @SerializedName("startIndex")
    val startIndex: Int? = null,
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("total")
    val total: Int? = null,
    @SerializedName("userTier")
    val userTier: String? = null
)