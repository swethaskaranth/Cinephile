package com.kaizencoder.cinephile.ui

import com.kaizencoder.cinephile.model.MovieCategory
import kotlinx.serialization.Serializable

sealed class Route {
    @Serializable
    object HomeScreen: Route()
    @Serializable
    data class MovieListingScreen(val category: MovieCategory): Route()
    @Serializable
    data class MovieDetailScreen(val movieId: Int): Route()
}