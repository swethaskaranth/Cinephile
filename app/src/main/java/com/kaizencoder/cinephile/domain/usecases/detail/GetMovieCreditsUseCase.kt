package com.kaizencoder.cinephile.domain.usecases.detail

import com.kaizencoder.cinephile.common.Resource
import com.kaizencoder.cinephile.domain.model.Credits
import com.kaizencoder.cinephile.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMovieCreditsUseCase @Inject constructor(private val movieRepository: MovieRepository) {

    operator fun invoke(movieId: Int): Flow<Resource<Credits>>  = flow {
        emit(Resource.Loading())
        emit(movieRepository.getMovieCredits(movieId))
    }

}
