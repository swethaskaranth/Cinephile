package com.kaizencoder.cinephile.domain.usecases

import com.kaizencoder.cinephile.data.networking.Resource
import com.kaizencoder.cinephile.data.networking.dto.toMovieDetail
import com.kaizencoder.cinephile.domain.model.MovieDetail
import com.kaizencoder.cinephile.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(private val movieRepository: MovieRepository) {

    operator fun invoke(movieId: Int): Flow<Resource<MovieDetail>> = flow {
        try {
            emit(Resource.Loading<MovieDetail>())
            val detail = movieRepository.getMovieDetails(movieId).toMovieDetail()
            emit(Resource.Success(detail))
        } catch (e: HttpException) {
            emit(
                Resource.Error<MovieDetail>(
                    e.localizedMessage ?: "Unexpected error occurred. Please try again in sometime."
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.Error<MovieDetail>(
                    e.localizedMessage
                        ?: "Couldn't reach server. Please check your internet connection."
                )
            )
        }
    }
}