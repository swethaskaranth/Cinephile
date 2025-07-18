package com.kaizencoder.cinephile.networking

import com.kaizencoder.cinephile.networking.response.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    @GET("movie/popular")
    suspend fun getMovies(@Query("page") page : Int, @Query("language") language: String = "en-US"): MovieListResponse
}