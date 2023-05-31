package com.petland.app.data.model.remote.response

import com.google.gson.annotations.SerializedName

data class PetType(
    @SerializedName("id") val id:Int = -1,
    @SerializedName("petType") val petType: String = "",
)
