package com.example.wikipediaapp

import Json4Kotlin_Base
import android.content.Context
import com.example.wikipediaapp.api.RetrofitInstance
import com.example.wikipediaapp.data.WikiDao
import com.example.wikipediaapp.data.WikiDatabase
import com.example.wikipediaapp.data.WikiPage
import retrofit2.Response

class Repository(context: Context) {
    var wikiDao: WikiDao = WikiDatabase.getDatabase(context)?.wikiDao()

    suspend fun getSearchResults(s: String): Response<Json4Kotlin_Base> {
        return RetrofitInstance.api.getSearchResult(s)
    }

    suspend fun getSearchFromDb(s: String): List<WikiPage> {
       return  wikiDao.getFromDb(s)
    }

    suspend fun InsertPage(page: WikiPage) {
        wikiDao.insertPage(page)
    }


}