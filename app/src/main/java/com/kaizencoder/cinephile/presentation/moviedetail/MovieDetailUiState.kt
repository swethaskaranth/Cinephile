package com.kaizencoder.cinephile.presentation.moviedetail

import com.kaizencoder.cinephile.domain.model.MovieDetail

data class MovieDetailUiState(
    val movieDetail: MovieDetail? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
