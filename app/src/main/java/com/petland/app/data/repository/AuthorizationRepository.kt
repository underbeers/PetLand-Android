package com.petland.app.data.repository

import com.petland.app.data.ApiService
import com.petland.app.data.model.remote.body.LoginBody
import com.petland.app.data.model.remote.body.SendCodeBody
import com.petland.app.data.model.remote.body.User
import com.petland.app.data.model.remote.response.LoginResponse
import com.petland.app.data.store.Store
import com.petland.app.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

class AuthorizationRepository @Inject constructor(
    private val apiService: ApiService, private val store: Store
) {
    suspend fun logIn(login: String, password: String): Flow<DataState<LoginResponse>> = flow {
        try {
            val response = apiService.logIn(LoginBody(login, password))
            store.setAccessToken(response.accessToken)
            emit(DataState.Success(response))
        } catch (e: Exception) {
            Timber.e("login error", e.message)
            emit(DataState.Error(e))
        }
    }

    suspend fun signUp(user: User): Flow<DataState<Unit>> = flow {
        try {
            val response = apiService.signUp(user)
            emit(DataState.Success(response))
        } catch (e: Exception) {
            Timber.e("sign up error", e.message)
            emit(DataState.Error(e))
        }
    }

    suspend fun sendCode(email: String, code: String): Flow<DataState<Unit>> = flow {
        try {
            val response = apiService.sendCode(SendCodeBody(email, code))
            emit(DataState.Success(response))
        } catch (e: Exception) {
            Timber.e("send code error", e.message)
            emit(DataState.Error(e))
        }
    }
}