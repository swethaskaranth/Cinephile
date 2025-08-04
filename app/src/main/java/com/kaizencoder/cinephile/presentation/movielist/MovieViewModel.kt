package com.kaizencoder.cinephile.presentation.movielist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.kaizencoder.cinephile.domain.model.Movie
import com.kaizencoder.cinephile.domain.usecases.movie.GetNowPlayingMoviesUseCase
import com.kaizencoder.cinephile.domain.usecases.movie.GetPopularMoviesUseCase
import com.kaizencoder.cinephile.domain.usecases.movie.GetTopRatedMoviesUseCase
import com.kaizencoder.cinephile.domain.usecases.movie.GetUpcomingMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    getPopularMoviesUseCase: GetPopularMoviesUseCase,
    getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase,
    getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,
    getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase
) : ViewModel() {

    val popularMovies: Flow<PagingData<Movie>> = getPopularMoviesUseCase().cachedIn(viewModelScope)
    val nowPlayingMovies: Flow<PagingData<Movie>> =
        getNowPlayingMoviesUseCase().cachedIn(viewModelScope)
    val topRatedMovies: Flow<PagingData<Movie>> =
        getTopRatedMoviesUseCase().cachedIn(viewModelScope)
    val upcomingMovies: Flow<PagingData<Movie>> =
        getUpcomingMoviesUseCase().cachedIn(viewModelScope)

}