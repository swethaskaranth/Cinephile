package com.kaizencoder.cinephile.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.kaizencoder.cinephile.networking.response.Cast
import com.kaizencoder.cinephile.networking.response.Crew
import com.kaizencoder.cinephile.networking.response.MovieCreditsResponse
import com.kaizencoder.cinephile.networking.response.MovieDetail
import com.kaizencoder.cinephile.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    repository: MovieRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val args = savedStateHandle.toRoute<Route.MovieDetailScreen>()
    val movie: Flow<MovieDetail> = repository.getMovieDetails(args.movieId)

    data class CreditsUiState(
        val cast: List<Cast> = emptyList(),
        val directors: List<Crew> = emptyList(),
        val writers: List<Crew> = emptyList(),
        val isLoading: Boolean = false,
        val error: String? = null
    )

    private val _creditsUiState: MutableStateFlow<CreditsUiState> =
        MutableStateFlow(CreditsUiState())
    val creditsUiState: StateFlow<CreditsUiState> = _creditsUiState.asStateFlow()

    init {
        viewModelScope.launch {
            _creditsUiState.update { it.copy(isLoading = true, error = null) }
            repository.getMovieCredits(args.movieId)
                .catch { error ->
                    _creditsUiState.update {
                        it.copy(
                            isLoading = false,
                            error = error.localizedMessage
                        )
                    }
                }
                .collect { response ->
                    _creditsUiState.update {
                        it.copy(
                            cast = response.cast.filter { it.known_for_department == "Acting" && it.order < 10 },
                            directors = response.crew.filter { it.job == "Director" },
                            writers = response.crew.filter { it.job == "Screenplay" },
                            isLoading = false,
                            error = null
                        )
                    }
                }
        }
    }

}