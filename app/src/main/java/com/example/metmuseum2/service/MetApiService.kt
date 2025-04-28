package com.example.metmuseum2.service

import com.example.metmuseum2.model.ArtworkDetailResponse
import com.example.metmuseum2.model.ObjectIDsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MetApiService {
    @GET("public/collection/v1/objects")
    suspend fun getObjectIDs(): ObjectIDsResponse

    @GET("public/collection/v1/objects/{id}")
    suspend fun getObjectDetails(@Path("id") id: Int): ArtworkDetailResponse
}