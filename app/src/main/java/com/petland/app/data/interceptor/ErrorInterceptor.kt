package com.petland.app.data.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class ErrorInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val builder = original.newBuilder()
        val response = chain.proceed(builder.build())
        val code = response.code
        val body = response.peekBody(RESPONSE_SIZE).string()
        return response
    }

    companion object {
        private const val RESPONSE_SIZE = Long.MAX_VALUE
    }

}