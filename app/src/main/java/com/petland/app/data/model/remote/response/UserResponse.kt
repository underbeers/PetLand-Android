package com.petland.app.data.model.remote.response

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("firstName") val name: String,
    @SerializedName("surName") val surname: String,
    @SerializedName("email") val email: String,
    @SerializedName("userID") val userId: String,
    @SerializedName("chatID") val chatId: String,
    @SerializedName("sessionID") val sessionID: String,
    @SerializedName("imageLink") val imageLink: String,
)
