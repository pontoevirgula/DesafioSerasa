package com.chslcompany.desafioserasa.domain.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CharactersResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("status")
    val status: String,
    @SerializedName("copyright")
    val copyright: String,
    @SerializedName("attributionText")
    val attributionText: String,
    @SerializedName("attributionHTML")
    val attributionHTML: String,
    @SerializedName("etag")
    val eTag: String,
    @SerializedName("data")
    val data: Data
) : Serializable

data class Data(
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("total")
    val total: Int,
    @SerializedName("count")
    val count: Int,
    @SerializedName("results")
    val results: List<Results>
) : Serializable