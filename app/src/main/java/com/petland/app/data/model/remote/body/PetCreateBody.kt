package com.petland.app.data.model.remote.body

import com.google.gson.annotations.SerializedName

data class PetCreateBody(
    @SerializedName("petTypeID") val petTypeID: Int,
    @SerializedName("petName") val petName: String,
    @SerializedName("breedID") val breedID: Int,
    @SerializedName("photo") val photo: String,
    @SerializedName("thumbnailPhoto") val thumbnailPhoto: String,
    @SerializedName("birthDate") val birthDate: String,
    @SerializedName("male") val male: Boolean,
    @SerializedName("color") val color: String,
    @SerializedName("care") val care: String,
    @SerializedName("petCharacter") val petCharacter: String,
    @SerializedName("pedigree") val pedigree: String,
    @SerializedName("sterilization") val sterilization: Boolean,
    @SerializedName("vaccinations") val vaccinations: Boolean
)
