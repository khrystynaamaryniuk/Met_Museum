package com.example.metmuseum2.model

import com.google.gson.annotations.SerializedName

data class ObjectIDsResponse(
    @SerializedName("total") val total: Int,
    @SerializedName("objectIDs") val objectIDs: List<Int>
)