package com.petland.app.data.model.remote.response

import com.google.gson.annotations.SerializedName


data class LoginResponse(
    @SerializedName("accessToken") val accessToken: String
)