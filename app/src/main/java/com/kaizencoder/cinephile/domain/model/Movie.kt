package com.kaizencoder.cinephile.domain.model

data class Movie(
    val movieId: Int,
    val title: String,
    val posterPath: String?,
    val releaseDate: String,
    val voteAverage: Double,
    val voteCount: Int
)
