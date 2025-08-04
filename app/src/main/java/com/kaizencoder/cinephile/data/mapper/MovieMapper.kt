package com.kaizencoder.cinephile.data.mapper

import com.kaizencoder.cinephile.data.networking.movie.MovieDetailDto
import com.kaizencoder.cinephile.data.networking.movie.MovieDto
import com.kaizencoder.cinephile.domain.model.Movie
import com.kaizencoder.cinephile.domain.model.MovieDetail
import java.util.Locale


fun MovieDto.toMovie(): Movie = Movie(
    movieId = id,
    title = title,
    posterPath = poster_path.orEmpty(),
    releaseDate = release_date,
    voteAverage = vote_average,
    voteCount = vote_count
)

fun MovieDetailDto.toMovieDetail(): MovieDetail = MovieDetail(
    movieId = id,
    title = title,
    overview = overview,
    genres = genres.map { it.name },
    posterPath = poster_path.orEmpty(),
    releaseDate = release_date,
    rating = String.format(Locale.US, "%.1f", vote_average),
    totalVotes = if (vote_count > 1000) "${vote_count / 1000}K" else vote_count.toString()
)