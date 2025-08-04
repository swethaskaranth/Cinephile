package com.kaizencoder.cinephile.data.networking.movie

import com.kaizencoder.cinephile.domain.model.Movie

data class MovieListDto(
    val page: Int,
    val results: List<MovieDto>,
    val total_pages: Int,
    val total_results: Int
)

data class MovieDto(
    val adult: Boolean,
    val backdrop_path: String?,
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String?,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)
