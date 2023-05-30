package com.petland.app.data


import com.petland.app.data.model.remote.body.LoginBody
import com.petland.app.data.model.remote.body.SendCodeBody
import com.petland.app.data.model.remote.response.LoginResponse
import com.petland.app.data.model.remote.body.UserBody
import com.petland.app.data.model.remote.response.UserResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    @POST(ApiURL.SIGN_UP_USER)
    suspend fun signUp(@Body userBody: UserBody)

    @POST(ApiURL.LOG_IN_USER)
    suspend fun logIn(@Body loginBody: LoginBody): LoginResponse

    @POST(ApiURL.SEND_CODE)
    suspend fun sendCode(@Body sendCodeBody: SendCodeBody)

    @GET(ApiURL.GET_USER_INFO)
    suspend fun getUserInfo(@Header("authorization") access: String ): UserResponse
}