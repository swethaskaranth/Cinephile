package com.kaizencoder.cinephile.data.mapper

import com.kaizencoder.cinephile.data.networking.movie.MovieDetailDto
import com.kaizencoder.cinephile.data.networking.movie.MovieDto
import com.kaizencoder.cinephile.domain.model.Movie
import com.kaizencoder.cinephile.domain.model.MovieDetail
import java.util.Locale


fun MovieDto.toMovie(): Movie = Movie(
    movieId = id,
    title = title,
    posterPath = posterPath.orEmpty(),
    releaseDate = releaseDate,
    voteAverage = voteAverage,
    voteCount = voteCount
)

fun MovieDetailDto.toMovieDetail(): MovieDetail = MovieDetail(
    movieId = id,
    title = title,
    overview = overview,
    genres = genres.map { it.name },
    posterPath = posterPath.orEmpty(),
    releaseDate = releaseDate,
    rating = String.format(Locale.US, "%.1f", voteAverage),
    totalVotes = if (voteCount > 1000) "${voteCount / 1000}K" else voteCount.toString()
)
