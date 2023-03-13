package com.petland.app.data.model.remote.body

import com.google.gson.annotations.SerializedName

data class SendCodeBody(
    @SerializedName("email") val email: String,
    @SerializedName("code") val code: String,
)
