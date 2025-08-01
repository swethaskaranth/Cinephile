package com.kaizencoder.cinephile.domain.repository

import androidx.paging.PagingData
import com.kaizencoder.cinephile.data.networking.dto.MovieCreditsDto
import com.kaizencoder.cinephile.data.networking.dto.MovieDetailDto
import com.kaizencoder.cinephile.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getPopularMovies(): Flow<PagingData<Movie>>

    fun getTopRatedMovies(): Flow<PagingData<Movie>>

    fun getNowPlayingMovies(): Flow<PagingData<Movie>>

    fun getUpcomingMovies(): Flow<PagingData<Movie>>

    suspend fun getMovieDetails(movieID: Int): MovieDetailDto

    suspend fun getMovieCredits(movieId: Int): MovieCreditsDto

}