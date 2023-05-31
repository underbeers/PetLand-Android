package com.petland.app.data.model.remote.response

import com.google.gson.annotations.SerializedName

data class FavoritesResponse(
    @SerializedName("adverts") val adverts: List<Advert>,
    @SerializedName("organizations") val organizations: List<Organization>,
    @SerializedName("specialists") val specialists: List<Specialist>,
)

data class Advert(
    @SerializedName("favoritesID") val favoritesId: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("petCardID") val petCardId: Int,
    @SerializedName("userID") val userId: String,
    @SerializedName("petName") val petName: String,
    @SerializedName("mainPhoto") val mainPhoto: String,
    @SerializedName("price") val price: Int,
    @SerializedName("description") val description: String,
    @SerializedName("city") val city: String,
    @SerializedName("district") val district: String,
    @SerializedName("publication") val publication: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("birthDate") val birthDate: String,
    @SerializedName("petType") val petType: String,
    @SerializedName("breed") val breed: String
)

data class Organization(
    @SerializedName("favoritesID") val favoritesId: Int,
    @SerializedName("id") val id: Int
)

data class Specialist(
    @SerializedName("favoritesID") val favoritesId: Int,
    @SerializedName("id") val id: Int
)






