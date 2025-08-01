package com.kaizencoder.cinephile.data.networking.dto

import com.kaizencoder.cinephile.domain.model.MovieDetail
import java.util.Locale

data class MovieDetailDto(
    val adult: Boolean,
    val backdrop_path: String?,
    val belongs_to_collection: Any?,
    val budget: Int,
    val genres: List<Genre>,
    val homepage: String?,
    val id: Int,
    val imdb_id: String?,
    val origin_country: List<String>?,
    val original_language: String?,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String?,
    val production_companies: List<ProductionCompany>,
    val production_countries: List<ProductionCountry>,
    val release_date: String,
    val revenue: Int,
    val runtime: Int,
    val spoken_languages: List<SpokenLanguage>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)

data class Genre(
    val id: Int,
    val name: String
)

data class ProductionCompany(
    val id: Int,
    val logo_path: String?,
    val name: String,
    val origin_country: String
)

data class ProductionCountry(
    val iso_3166_1: String,
    val name: String
)

data class SpokenLanguage(
    val english_name: String,
    val iso_639_1: String,
    val name: String
)

fun MovieDetailDto.toMovieDetail(): MovieDetail = MovieDetail(
    movieId = id,
    title = title,
    overview = overview,
    genres = genres.map { it.name },
    posterPath = poster_path,
    releaseDate = release_date,
    rating = String.format(Locale.US, "%.1f", vote_average),
    totalVotes = if (vote_count > 1000) "${vote_count / 1000}K" else vote_count.toString()
)