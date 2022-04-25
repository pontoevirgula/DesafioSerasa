package com.chslcompany.desafioserasa.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "results_table")
data class Results(
    @PrimaryKey
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("modified")
    val modified: String,
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail,
    @SerializedName("resourceURI")
    val resourceURI: String,
    @SerializedName("comics")
    val comics: Comics,
    @SerializedName("series")
    val series: Series,
    @SerializedName("stories")
    val stories: Stories,
    @SerializedName("events")
    val events: Events,
    @SerializedName("urls")
    val urls: List<Urls>
) : Serializable


data class Thumbnail(
    @SerializedName("path")
    val path: String,
    @SerializedName("extension")
    val extension: String
) : Serializable

data class Comics(
    @SerializedName("available")
    val available: Int,
    @SerializedName("collectionURI")
    val collectionURI: String,
    @SerializedName("items")
    val items: List<Items>,
    @SerializedName("returned")
    val returned: Int
) : Serializable

class Items(
    @SerializedName("resourceURI")
    val resourceURI: String,
    @SerializedName("name")
    val name: String
) : Serializable

data class Stories (
    @SerializedName("available")
    val available : Int,
    @SerializedName("collectionURI")
    val collectionURI : String,
    @SerializedName("items")
    val items : List<Items>,
    @SerializedName("returned")
    val returned : Int
): Serializable

data class Series(
    @SerializedName("available")
    val available: Int,
    @SerializedName("collectionURI")
    val collectionURI: String,
    @SerializedName("items")
    val items: List<Items>,
    @SerializedName("returned")
    val returned: Int
) : Serializable

data class Urls(
    @SerializedName("type")
    val type: String,
    @SerializedName("url")
    val url: String
) : Serializable

data class Events (
    @SerializedName("available")
    val available : Int,
    @SerializedName("collectionURI")
    val collectionURI : String,
    @SerializedName("items")
    val items : List<Items>,
    @SerializedName("returned")
    val returned : Int
): Serializable