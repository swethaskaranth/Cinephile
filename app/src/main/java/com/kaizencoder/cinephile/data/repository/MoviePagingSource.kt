package com.kaizencoder.cinephile.data.repository

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kaizencoder.cinephile.data.networking.APIService
import com.kaizencoder.cinephile.data.networking.dto.toMovie
import com.kaizencoder.cinephile.domain.model.Movie
import com.kaizencoder.cinephile.domain.model.MovieCategory

class MoviePagingSource(private val apiService: APIService, private val category: MovieCategory): PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
       return state.anchorPosition?.let { anchor ->
            state.closestPageToPosition(anchor)?.prevKey?.plus(1) ?:
            state.closestPageToPosition(anchor)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: 1



        return try {
            val results = when(category){
                MovieCategory.POPULAR -> apiService.getPopularMovies(page)
                MovieCategory.NOW_PLAYING -> apiService.getNowPlayingMovies(page)
                MovieCategory.TOP_RATED -> apiService.getTopRatedMovies(page)
                MovieCategory.UPCOMING -> apiService.getUpcomingMovies(page)
            }
            Log.i("MovieListScreen", "Fetched results ${results.results.size}")
            LoadResult.Page(
                data = results.results.map { it.toMovie() },
                prevKey = if(page == 1) null else page.minus(1),
                nextKey = if(page == results.total_pages) null else page.plus(1)
            )
        }catch (ex : Exception){
            ex.printStackTrace()
            Log.i("MovieListScreen", "Threw exception ${ex.stackTrace}")
            LoadResult.Error(ex)
        }
    }
}