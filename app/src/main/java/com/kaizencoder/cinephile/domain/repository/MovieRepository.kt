package com.kaizencoder.cinephile.domain.repository

import androidx.paging.PagingData
import com.kaizencoder.cinephile.common.Resource
import com.kaizencoder.cinephile.domain.model.Credits
import com.kaizencoder.cinephile.domain.model.Movie
import com.kaizencoder.cinephile.domain.model.MovieDetail
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getPopularMovies(): Flow<PagingData<Movie>>

    fun getTopRatedMovies(): Flow<PagingData<Movie>>

    fun getNowPlayingMovies(): Flow<PagingData<Movie>>

    fun getUpcomingMovies(): Flow<PagingData<Movie>>

    suspend fun getMovieDetails(movieID: Int): Resource<MovieDetail>

    suspend fun getMovieCredits(movieId: Int): Resource<Credits>

}