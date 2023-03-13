package com.petland.app.di

import com.petland.app.data.ApiService
import com.petland.app.data.ApiURL
import com.petland.app.data.interceptor.ErrorInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideBaseURL(): String {
        return ApiURL.BASE_URL
    }

    @Singleton
    @Provides
    fun provideConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideErrorInterceptor(): Interceptor {
        return ErrorInterceptor()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(errorInterceptor: Interceptor): OkHttpClient {
        val okHttpClient = OkHttpClient().newBuilder()
        okHttpClient.callTimeout(15, TimeUnit.SECONDS)
        okHttpClient.connectTimeout(15, TimeUnit.SECONDS)
        okHttpClient.readTimeout(15, TimeUnit.SECONDS)
        okHttpClient.writeTimeout(15, TimeUnit.SECONDS)
        okHttpClient.addInterceptor(errorInterceptor)
        okHttpClient.build()
        return okHttpClient.build()
    }

    @Singleton
    @Provides
    fun provideRetrofitClient(
        baseUrl: String,
        converterFactory: Converter.Factory,
        okHttpClient: OkHttpClient,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(converterFactory)
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideRestApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

}