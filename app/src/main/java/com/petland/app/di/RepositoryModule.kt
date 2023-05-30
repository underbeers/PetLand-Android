package com.petland.app.di

import com.petland.app.data.ApiService
import com.petland.app.data.repository.AccountRepository
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
    ): AccountRepository {
        return AccountRepository(apiService)
    }
}
