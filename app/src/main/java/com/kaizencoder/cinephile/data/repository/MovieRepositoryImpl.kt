package com.kaizencoder.cinephile.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.kaizencoder.cinephile.data.networking.APIService
import com.kaizencoder.cinephile.data.networking.dto.MovieCreditsDto
import com.kaizencoder.cinephile.data.networking.dto.MovieDetailDto
import com.kaizencoder.cinephile.domain.model.Movie
import com.kaizencoder.cinephile.domain.model.MovieCategory
import com.kaizencoder.cinephile.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val apiService: APIService) :
    MovieRepository {

    override fun getPopularMovies(): Flow<PagingData<Movie>> {
        val config = PagingConfig(
            pageSize = 20
        )
        return Pager(
            config = config,
            pagingSourceFactory = { MoviePagingSource(apiService, MovieCategory.POPULAR) }
        ).flow
    }

    override fun getTopRatedMovies(): Flow<PagingData<Movie>> {
        val config = PagingConfig(
            pageSize = 20
        )
        return Pager(
            config = config,
            pagingSourceFactory = { MoviePagingSource(apiService, MovieCategory.TOP_RATED) }
        ).flow
    }

    override fun getNowPlayingMovies(): Flow<PagingData<Movie>> {
        val config = PagingConfig(
            pageSize = 20
        )
        return Pager(
            config = config,
            pagingSourceFactory = { MoviePagingSource(apiService, MovieCategory.NOW_PLAYING) }
        ).flow
    }

    override fun getUpcomingMovies(): Flow<PagingData<Movie>> {
        val config = PagingConfig(
            pageSize = 20
        )
        return Pager(
            config = config,
            pagingSourceFactory = { MoviePagingSource(apiService, MovieCategory.UPCOMING) }
        ).flow
    }

    override suspend fun getMovieDetails(movieID: Int): MovieDetailDto {
        return apiService.getMovieDetails(movieID)
    }

    override suspend fun getMovieCredits(movieId: Int): MovieCreditsDto {
        return apiService.getMovieCredits(movieId)
    }
}