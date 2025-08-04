package com.kaizencoder.cinephile.presentation.moviedetail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.kaizencoder.cinephile.data.networking.Resource
import com.kaizencoder.cinephile.domain.usecases.detail.GetMovieCreditsUseCase
import com.kaizencoder.cinephile.domain.usecases.detail.GetMovieDetailsUseCase
import com.kaizencoder.cinephile.presentation.common.navigation.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val getMovieCreditsUseCase: GetMovieCreditsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val args = savedStateHandle.toRoute<Route.MovieDetailScreen>()

    private val _movieDetailUiState: MutableState<MovieDetailUiState> =
        mutableStateOf(MovieDetailUiState())
    val movieDetailUiState: State<MovieDetailUiState> = _movieDetailUiState

    private val _creditsUiState: MutableState<CreditsUiState> =
        mutableStateOf(CreditsUiState())
    val creditsUiState: State<CreditsUiState> = _creditsUiState

    init {
        getMovieDetails()
        getCredits()
    }

    private fun getCredits() {
        getMovieCreditsUseCase(args.movieId)
            .onEach { result ->
                when (result) {
                    is Resource.Loading -> {
                        _creditsUiState.value =
                            CreditsUiState(isLoading = true)
                    }

                    is Resource.Error -> {
                        _creditsUiState.value =
                            CreditsUiState(error = result.message)
                    }

                    is Resource.Success -> {
                        val data = result.data
                        _creditsUiState.value = CreditsUiState(
                            cast = data?.castMembers ?: emptyList(),
                            directors = data?.directors ?: emptyList(),
                            writers = data?.writers ?: emptyList()
                        )
                    }
                }
            }.launchIn(viewModelScope)
    }

    private fun getMovieDetails() {
        getMovieDetailsUseCase(args.movieId)
            .onEach { result ->
                when (result) {
                    is Resource.Loading -> {
                        _movieDetailUiState.value = MovieDetailUiState(isLoading = true)
                    }

                    is Resource.Error -> {
                        _movieDetailUiState.value = MovieDetailUiState(error = result.message)
                    }

                    is Resource.Success -> {
                        _movieDetailUiState.value =
                            MovieDetailUiState(movieDetail = result.data)
                    }
                }
            }.launchIn(viewModelScope)
    }

}