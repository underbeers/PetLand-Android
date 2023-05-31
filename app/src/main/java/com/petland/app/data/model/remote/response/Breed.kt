package com.petland.app.data.model.remote.response

import com.google.gson.annotations.SerializedName

data class Breed(
    @SerializedName("id") val id: Int = -1,
    @SerializedName("petTypeID") val petTypeId: Int = -1,
    @SerializedName("breedName") val breedName: String = ""
)