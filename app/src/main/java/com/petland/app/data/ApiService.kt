package com.petland.app.data


import com.petland.app.data.model.remote.body.LoginBody
import com.petland.app.data.model.remote.body.PetCreateBody
import com.petland.app.data.model.remote.body.SendCodeBody
import com.petland.app.data.model.remote.response.LoginResponse
import com.petland.app.data.model.remote.body.UserBody
import com.petland.app.data.model.remote.response.Breed
import com.petland.app.data.model.remote.response.Pet
import com.petland.app.data.model.remote.response.PetCardFull
import com.petland.app.data.model.remote.response.PetType
import com.petland.app.data.model.remote.response.UserResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT

interface ApiService {
    @POST(ApiURL.SIGN_UP_USER)
    suspend fun signUp(@Body userBody: UserBody)

    @POST(ApiURL.LOG_IN_USER)
    suspend fun logIn(@Body loginBody: LoginBody): LoginResponse

    @POST(ApiURL.SEND_CODE)
    suspend fun sendCode(@Body sendCodeBody: SendCodeBody)

    @GET(ApiURL.GET_USER_INFO)
    suspend fun getUserInfo(@Header("authorization") access: String): UserResponse

    @GET(ApiURL.GET_PET_CARDS)
    suspend fun getPetCards(): List<Pet>

    @GET(ApiURL.GET_PETS)
    suspend fun getFullCards(): List<PetCardFull>

    @POST(ApiURL.CREATE_PET_CARD)
    suspend fun createPet(@Body petCreateBody: PetCreateBody)

    @PUT(ApiURL.UPDATE_PET_CARD)
    suspend fun editPetCard(@Body petCreateBody: PetCreateBody)

    @GET(ApiURL.BREEDS)
    suspend fun getBreeds(): List<Breed>

    @GET(ApiURL.PET_TYPES)
    suspend fun getPetType(): List<PetType>

}