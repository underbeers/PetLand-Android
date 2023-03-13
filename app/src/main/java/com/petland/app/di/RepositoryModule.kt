package com.petland.app.di

import com.petland.app.data.ApiService
import com.petland.app.data.repository.AuthorizationRepository
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
    ): AuthorizationRepository {
        return AuthorizationRepository(
            apiService
        )
    }
}
