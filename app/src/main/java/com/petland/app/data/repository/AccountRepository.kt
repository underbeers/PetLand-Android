package com.petland.app.data.repository

import com.petland.app.data.ApiService
import com.petland.app.data.model.remote.body.User
import com.petland.app.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

class AccountRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getUserInfo(): Flow<DataState<User>> = flow {
        try {
            val userInfo = apiService.getUserInfo()
            emit(DataState.Success(userInfo))
        } catch (exception : Exception){
            Timber.e("get user error", exception.message)
            emit(DataState.Error(exception))
        }
    }
}