package com.example.wikipediaapp.model

import com.google.gson.annotations.SerializedName

data class Terms(

    @SerializedName("alias") val alias: List<String>,
    @SerializedName("label") val label: List<String>,
    @SerializedName("description") val description: List<String?>
)