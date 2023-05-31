package com.petland.app.data.model.remote.response

import com.google.gson.annotations.SerializedName

data class Photo(
    @SerializedName("thumbnail") val thumbnail: String,
    @SerializedName("original") val original: String
)
