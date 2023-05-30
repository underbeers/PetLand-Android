package com.petland.app.data.repository

import com.petland.app.data.ApiService
import com.petland.app.data.model.remote.response.UserResponse
import com.petland.app.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

class AccountRepository @Inject constructor(
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
}