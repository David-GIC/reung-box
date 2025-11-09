package com.daviddev.reungbox.data.client

import com.daviddev.reungbox.data.remote.MovieApi
import com.daviddev.reungbox.utils.ConstantValue
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiInstance {
    val okHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader(ConstantValue.rapidHostKey, ConstantValue.rapidHostValue).addHeader(
                        ConstantValue.rapidApiKey, ConstantValue.rapidApiKeyValue)
                    .build()
                chain.proceed(request)
            }
            .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
            .build()
    }

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(ConstantValue.baseURL)      // Your API base URL
            .client(okHttpClient)                     // Use custom OkHttp client
            .addConverterFactory(MoshiConverterFactory.create()) // JSON parsing
            .build()
    }

    val movieApi: MovieApi by lazy {
        retrofit.create(MovieApi::class.java)
    }
}