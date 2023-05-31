package com.petland.app.data.model.remote.response

import com.google.gson.annotations.SerializedName

data class PetCardFull(
    @SerializedName("id") val id: Int,
    @SerializedName("petTypeID") val petTypeID: Int,
    @SerializedName("petType") val petType: String,
    @SerializedName("userID") val userID: String,
    @SerializedName("petName") val petName: String,
    @SerializedName("breedID") val breedID: Int,
    @SerializedName("breed") val breed: String,
    @SerializedName("photos") val photos: List<Photo>,
    @SerializedName("birthDate") val birthDate: String,
    @SerializedName("male") val male: Boolean,
    @SerializedName("gender") val gender: String,
    @SerializedName("color") val color: String,
    @SerializedName("care") val care: String,
    @SerializedName("petCharacter") val petCharacter: String,
    @SerializedName("pedigree") val pedigree: String,
    @SerializedName("sterilization") val sterilization: Boolean,
    @SerializedName("vaccinations") val vaccinations: Boolean
)
