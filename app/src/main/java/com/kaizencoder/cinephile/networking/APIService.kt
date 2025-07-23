package com.kaizencoder.cinephile.networking

import com.kaizencoder.cinephile.networking.response.MovieDetail
import com.kaizencoder.cinephile.networking.response.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("page") page : Int, @Query("language") language: String = "en-US"): MovieListResponse

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(@Query("page") page : Int, @Query("language") language: String = "en-US"): MovieListResponse

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query("page") page : Int, @Query("language") language: String = "en-US"): MovieListResponse

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(@Query("page") page : Int, @Query("language") language: String = "en-US"): MovieListResponse

    @GET("movie/{movieId}")
    suspend fun getMovieDetails(@Path("movieId") id: Int): MovieDetail
}