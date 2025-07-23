package com.kaizencoder.cinephile.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.kaizencoder.cinephile.networking.response.MovieDetail
import com.kaizencoder.cinephile.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    repository: MovieRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val args = savedStateHandle.toRoute<Route.MovieDetailScreen>()
    val movie: Flow<MovieDetail> = repository.getMovieDetails(args.movieId)

}