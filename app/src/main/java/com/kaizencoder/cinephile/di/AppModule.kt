package com.kaizencoder.cinephile.di

import android.util.Log
import com.kaizencoder.cinephile.BuildConfig
import com.kaizencoder.cinephile.Constants
import com.kaizencoder.cinephile.networking.APIService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import kotlin.math.log

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun moshi() = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    fun httpLoggingInterceptor(): HttpLoggingInterceptor{
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Provides
    fun okHttpClient(loggingInterceptor: HttpLoggingInterceptor) = OkHttpClient.Builder()
        .addInterceptor{ chain ->
            val original = chain.request()
            val token = BuildConfig.API_TOKEN
            Log.i("MovieListScreen","Token added ${token}")
            val request = original.newBuilder()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer $token")
                .build()
            chain.proceed(request)
        }
        .addInterceptor(loggingInterceptor)
        .build()

    @Provides
    fun retrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit{
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    fun apiService(retrofit: Retrofit) = retrofit.create<APIService>(APIService::class.java)
}