package com.kaizencoder.cinephile.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.kaizencoder.cinephile.data.mapper.toCredits
import com.kaizencoder.cinephile.data.mapper.toMovieDetail
import com.kaizencoder.cinephile.data.networking.APIService
import com.kaizencoder.cinephile.common.Resource
import com.kaizencoder.cinephile.domain.model.Credits
import com.kaizencoder.cinephile.domain.model.Movie
import com.kaizencoder.cinephile.domain.model.MovieCategory
import com.kaizencoder.cinephile.domain.model.MovieDetail
import com.kaizencoder.cinephile.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val apiService: APIService) :
    MovieRepository {

    override fun getPopularMovies(): Flow<PagingData<Movie>> = getMoviesPager(MovieCategory.POPULAR)

    override fun getTopRatedMovies(): Flow<PagingData<Movie>> =
        getMoviesPager(MovieCategory.TOP_RATED)

    override fun getNowPlayingMovies(): Flow<PagingData<Movie>> =
        getMoviesPager(MovieCategory.NOW_PLAYING)

    override fun getUpcomingMovies(): Flow<PagingData<Movie>> =
        getMoviesPager(MovieCategory.UPCOMING)

    private fun getMoviesPager(movieCategory: MovieCategory): Flow<PagingData<Movie>> {
        val config = PagingConfig(
            pageSize = 20
        )
        return Pager(
            config = config,
            pagingSourceFactory = { MoviePagingSource(apiService, movieCategory) }
        ).flow
    }

    override suspend fun getMovieDetails(movieID: Int): Resource<MovieDetail> {
        return try {
            val details = apiService.getMovieDetails(movieID).toMovieDetail()
            Resource.Success<MovieDetail>(details)
        } catch (e: HttpException) {
            Resource.Error<MovieDetail>(
                e.localizedMessage ?: "Unexpected error occurred. Please try again in sometime."
            )
        } catch (e: IOException) {
            Resource.Error<MovieDetail>(
                e.localizedMessage
                    ?: "Couldn't reach server. Please check your internet connection."
            )
        }
    }

    override suspend fun getMovieCredits(movieId: Int): Resource<Credits> {
        return try {
            val detail = apiService.getMovieCredits(movieId).toCredits()
            Resource.Success(detail)
        } catch (e: HttpException) {
            Resource.Error<Credits>(
                e.localizedMessage ?: "Unexpected error occurred. Please try again in sometime."
            )
        } catch (e: IOException) {
            Resource.Error<Credits>(
                e.localizedMessage
                    ?: "Couldn't reach server. Please check your internet connection."
            )
        }
    }
}
