package com.petland.app.data.model.remote.body

import com.google.gson.annotations.SerializedName

data class UserBody(
    @SerializedName("firstName") val name: String,
    @SerializedName("surName") val surname: String,
    @SerializedName("email") val email: String,
    @SerializedName("password")val password: String?,
)