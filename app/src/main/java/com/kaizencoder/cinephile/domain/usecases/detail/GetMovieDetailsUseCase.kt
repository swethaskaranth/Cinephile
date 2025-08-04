package com.kaizencoder.cinephile.domain.usecases.detail

import com.kaizencoder.cinephile.data.networking.Resource
import com.kaizencoder.cinephile.domain.model.MovieDetail
import com.kaizencoder.cinephile.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(private val movieRepository: MovieRepository) {

    operator fun invoke(movieId: Int): Flow<Resource<MovieDetail>> = flow {
        emit(movieRepository.getMovieDetails(movieId))
    }
}