package com.petland.app.data.repository

import com.petland.app.data.ApiService
import com.petland.app.data.model.remote.response.Pet
import com.petland.app.data.model.remote.response.UserResponse
import com.petland.app.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getUserInfo(access: String): Flow<DataState<UserResponse>> = flow {
        try {
            val userInfo = apiService.getUserInfo(access)
            emit(DataState.Success(userInfo))
        } catch (exception : Throwable){
            Timber.e("get user error", exception.message)
            emit(DataState.Error(exception))
        }
    }

    suspend fun getPetCards(): Flow<DataState<List<Pet>>> = flow {
        try {
            val petsCards = apiService.getPetCards()
            emit(DataState.Success(petsCards))
        } catch(exception: Throwable) {
            Timber.e("get pet cards error", exception.message)
            emit(DataState.Error(exception))
        }
    }
}