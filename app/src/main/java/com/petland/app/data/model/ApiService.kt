package com.petland.app.data.model


import com.petland.app.data.model.remote.body.LoginBody
import com.petland.app.data.model.remote.response.LoginResponse
import com.petland.app.data.model.remote.body.User
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST(ApiURL.SIGN_UP_USER)
    suspend fun signUp(user: User)

    @POST(ApiURL.LOG_IN_USER)
    suspend fun logIn(@Body loginBody: LoginBody): LoginResponse
}