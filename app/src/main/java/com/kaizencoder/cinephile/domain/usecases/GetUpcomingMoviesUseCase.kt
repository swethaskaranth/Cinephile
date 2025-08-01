package com.kaizencoder.cinephile.domain.usecases

import androidx.paging.PagingData
import com.kaizencoder.cinephile.domain.model.Movie
import com.kaizencoder.cinephile.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUpcomingMoviesUseCase @Inject constructor(private val movieRepository: MovieRepository) {

    operator fun invoke(): Flow<PagingData<Movie>> = movieRepository.getUpcomingMovies()
}