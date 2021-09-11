package com.example.wikipediaapp.api

import Json4Kotlin_Base
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SimpleApi {
    @GET("api.php?action=query&format=json&prop=pageimages%7Cpageterms&generator=prefixsearch&redirects=1&formatversion=2&piprop=thumbnail&pithumbsize=50&pilimit=10&wbptterms=description&gpslimit=10&gpsoffset=0")
    suspend fun getSearchResult(@Query("gpssearch") albert: String): Response<Json4Kotlin_Base>

    @GET("wiki/{albert}")
    suspend fun getPageResult(@Path("albert") albert: String)


}