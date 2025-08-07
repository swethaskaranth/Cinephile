package com.kaizencoder.cinephile.domain.usecases.movie

import com.kaizencoder.cinephile.domain.repository.MovieRepository
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(private val movieRepository: MovieRepository) {

    operator fun invoke() = movieRepository.getPopularMovies()

}
