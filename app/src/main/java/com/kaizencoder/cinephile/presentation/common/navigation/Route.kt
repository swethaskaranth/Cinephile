package com.kaizencoder.cinephile.presentation.common.navigation

import com.kaizencoder.cinephile.domain.model.MovieCategory
import kotlinx.serialization.Serializable

sealed class Route {
    @Serializable
    object HomeScreen: Route()
    @Serializable
    data class MovieListingScreen(val category: MovieCategory): Route()
    @Serializable
    data class MovieDetailScreen(val movieId: Int): Route()
}