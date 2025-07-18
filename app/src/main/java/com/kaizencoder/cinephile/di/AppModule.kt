package com.kaizencoder.cinephile.di

import com.kaizencoder.cinephile.Constants
import com.kaizencoder.cinephile.networking.APIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun okHttpClient() = OkHttpClient.Builder()
        .addInterceptor{ chain ->
            val original = chain.request()
            val token = Constants.API_TOKEN
            val request = original.newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
            chain.proceed(request)
        }
        .build()

    @Provides
    fun retrofit(okHttpClient: OkHttpClient): Retrofit{
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    fun apiService(retrofit: Retrofit) = retrofit.create<APIService>(APIService::class.java)
}