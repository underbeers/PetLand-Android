package com.petland.app.data.repository

import com.petland.app.data.model.ApiService
import com.petland.app.data.model.remote.body.LoginBody
import com.petland.app.data.model.remote.body.User
import com.petland.app.data.model.remote.response.LoginResponse
import com.petland.app.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthorizationRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun logIn(login: String, password: String): Flow<DataState<LoginResponse>> = flow {
        try {
            val response = apiService.logIn(LoginBody(login, password))
            emit(DataState.Success(response))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    suspend fun signUp(user: User): Flow<DataState<Unit>> = flow {
        try {
            emit(DataState.Success(apiService.signUp(user)))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}