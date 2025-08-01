package com.kaizencoder.cinephile.domain.usecases

import com.kaizencoder.cinephile.data.networking.Resource
import com.kaizencoder.cinephile.data.networking.dto.toCredits
import com.kaizencoder.cinephile.domain.model.Credits
import com.kaizencoder.cinephile.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMovieCreditsUseCase @Inject constructor(private val movieRepository: MovieRepository) {

    operator fun invoke(movieId: Int): Flow<Resource<Credits>>  = flow {
        try {
            emit(Resource.Loading<Credits>())
            val detail = movieRepository.getMovieCredits(movieId).toCredits()
           emit(Resource.Success(detail))
        } catch (e: HttpException) {
            emit(Resource.Error<Credits>(
                e.localizedMessage ?: "Unexpected error occurred. Please try again in sometime."
            ))
        } catch (e: IOException) {
            emit(Resource.Error<Credits>(
                e.localizedMessage
                    ?: "Couldn't reach server. Please check your internet connection."
            ))
        }
    }

}