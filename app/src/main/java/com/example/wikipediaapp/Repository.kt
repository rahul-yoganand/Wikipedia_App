package com.example.wikipediaapp

import Json4Kotlin_Base
import com.example.wikipediaapp.api.RetrofitInstance
import com.example.wikipediaapp.model.WikiPage
import retrofit2.Response

class Repository{

    suspend fun getSearchResults(s:String): Response<Json4Kotlin_Base> {
        return RetrofitInstance.api.getSearchResult(s)
    }
}