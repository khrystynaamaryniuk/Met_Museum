package com.example.metmuseum2.model

import com.google.gson.annotations.SerializedName

data class ArtworkDetailResponse(
    @SerializedName("title") val title: String,
    @SerializedName("artistDisplayName") val artistDisplayName: String,
    @SerializedName("primaryImage") val primaryImage: String,
    @SerializedName("objectDate") val objectDate: String
)