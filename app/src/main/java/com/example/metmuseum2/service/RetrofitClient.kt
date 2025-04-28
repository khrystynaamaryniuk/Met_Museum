package com.example.metmuseum2.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val api: MetApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://collectionapi.metmuseum.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MetApiService::class.java)
    }
}