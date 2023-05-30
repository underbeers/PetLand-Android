package com.petland.app.di

import android.content.Context
import com.petland.app.data.store.Store
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Singleton
    @Provides
    fun provideStore(@ApplicationContext context: Context): Store {
        return Store(context)
    }
}