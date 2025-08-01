package com.kaizencoder.cinephile.domain.model

data class MovieDetail(
    val movieId: Int,
    val title: String,
    val overview: String,
    val genres: List<String>,
    val posterPath: String?,
    val releaseDate: String,
    val rating: String,
    val totalVotes: String)
