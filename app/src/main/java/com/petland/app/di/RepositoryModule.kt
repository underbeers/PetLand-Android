package com.petland.app.di

import com.petland.app.data.ApiService
import com.petland.app.data.repository.PetCardRepository
import com.petland.app.data.repository.ProfileRepository
import com.petland.app.data.store.Store
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideAccountRepository(
        apiService: ApiService,
        store: Store
    ): ProfileRepository {
        return ProfileRepository(apiService)
    }

    @Singleton
    @Provides
    fun providePetCardRepository(
        apiService: ApiService,
    ): PetCardRepository {
        return PetCardRepository(apiService)
    }
}
