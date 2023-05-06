package com.petland.app.di

import com.petland.app.data.ApiService
import com.petland.app.data.repository.AccountRepository
import com.petland.app.data.repository.AuthorizationRepository
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
    fun provideMovieRepository(
        apiService: ApiService,
        store: Store,
    ): AuthorizationRepository {
        return AuthorizationRepository(
            apiService, store
        )
    }

    @Singleton
    @Provides
    fun provideAccountRepository(
        apiService: ApiService
    ): AccountRepository {
        return AccountRepository(apiService)
    }
}
