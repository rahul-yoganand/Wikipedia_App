package com.example.wikipediaapp.api


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy{
        Retrofit.Builder()
            .baseUrl("https://en.wikipedia.org/w/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
     val api: SimpleApi by lazy{
         retrofit.create(SimpleApi::class.java)
     }

}