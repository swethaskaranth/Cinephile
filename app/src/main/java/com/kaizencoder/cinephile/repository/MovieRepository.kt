package com.kaizencoder.cinephile.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.kaizencoder.cinephile.model.MovieCategory
import com.kaizencoder.cinephile.networking.APIService
import com.kaizencoder.cinephile.networking.response.Movie
import com.kaizencoder.cinephile.networking.response.MovieDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepository @Inject constructor(private val apiService: APIService) {

    fun getMovies(category: MovieCategory): Flow<PagingData<Movie>>{
        Log.i("MovieListScreen", "Calling pager")

        val config = PagingConfig(
            pageSize = 20
        )
        return Pager(
            config = config,
            pagingSourceFactory = { MoviePagingSource(apiService, category)}
        ).flow
    }

    fun getMovieDetails(movieID: Int): Flow<MovieDetail> = flow {
        emit(apiService.getMovieDetails(movieID))
    }
}