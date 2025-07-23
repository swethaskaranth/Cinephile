package com.kaizencoder.cinephile.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.kaizencoder.cinephile.model.MovieCategory
import com.kaizencoder.cinephile.networking.response.Movie
import com.kaizencoder.cinephile.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(repository: MovieRepository): ViewModel() {

    val popularMovies : Flow<PagingData<Movie>> = repository.getMovies(MovieCategory.POPULAR).cachedIn(viewModelScope)
    val nowPlayingMovies : Flow<PagingData<Movie>> = repository.getMovies(MovieCategory.NOW_PLAYING).cachedIn(viewModelScope)
    val topRatedMovies : Flow<PagingData<Movie>> = repository.getMovies(MovieCategory.TOP_RATED).cachedIn(viewModelScope)
    val upcomingMovies : Flow<PagingData<Movie>> = repository.getMovies(MovieCategory.UPCOMING).cachedIn(viewModelScope)

}