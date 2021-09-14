package com.example.wikipediaapp.model

import Thumbnail
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.wikipediaapp.model.Terms
import com.google.gson.annotations.SerializedName


data class Pages(

    @SerializedName("pageid") val pageid: Int,
    @SerializedName("ns") val ns: Int,
    @SerializedName("title") val title: String,
    @SerializedName("thumbnail") val thumbnail: Thumbnail,
    @SerializedName("pageimage") val pageimage: String,
    @SerializedName("terms") val terms: Terms
)