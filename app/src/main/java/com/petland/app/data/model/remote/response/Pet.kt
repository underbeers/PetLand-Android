package com.petland.app.data.model.remote.response


import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class Pet(
    @SerializedName("id") val id: Int,
    @SerializedName("petType") val petType: String,
    @SerializedName("petName") val petName: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("breed") val breed: String,
    @SerializedName("photo") val photo: String,
    @SerializedName("birthDate") val birthDate: String
)
