package com.kaizencoder.cinephile.data.networking

import com.kaizencoder.cinephile.data.networking.dto.MovieCreditsDto
import com.kaizencoder.cinephile.data.networking.dto.MovieDetailDto
import com.kaizencoder.cinephile.data.networking.dto.MovieListDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("page") page : Int, @Query("language") language: String = "en-US"): MovieListDto

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(@Query("page") page : Int, @Query("language") language: String = "en-US"): MovieListDto

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query("page") page : Int, @Query("language") language: String = "en-US"): MovieListDto

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(@Query("page") page : Int, @Query("language") language: String = "en-US"): MovieListDto

    @GET("movie/{movieId}")
    suspend fun getMovieDetails(@Path("movieId") id: Int): MovieDetailDto

    @GET("movie/{movieId}/credits")
    suspend fun getMovieCredits(@Path("movieId") id: Int, @Query("language") language: String = "en-US") : MovieCreditsDto
}