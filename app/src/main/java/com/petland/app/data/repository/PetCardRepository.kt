package com.petland.app.data.repository

import com.petland.app.data.ApiService
import com.petland.app.util.DataState
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

class PetCardRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getBreed()  = flow {
        try {
            val breeds = apiService.getBreeds()
            emit(DataState.Success(breeds))
        } catch (throwable: Throwable) {
            Timber.e("get breeds error")
            emit(DataState.Error(throwable))
        }
    }

    suspend fun getPetType()  = flow {
        try {
            val petTypes = apiService.getPetType()
            emit(DataState.Success(petTypes))
        } catch (throwable: Throwable) {
            Timber.e("get pet types error")
            emit(DataState.Error(throwable))
        }
    }
}