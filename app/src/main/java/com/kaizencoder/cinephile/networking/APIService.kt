package com.kaizencoder.cinephile.networking

import com.kaizencoder.cinephile.networking.response.MovieListResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface APIService {

    @GET("movie/popular")
    suspend fun getMovies(): Flow<MovieListResponse>
}