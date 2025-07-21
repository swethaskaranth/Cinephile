package com.kaizencoder.cinephile.repository

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kaizencoder.cinephile.networking.APIService
import com.kaizencoder.cinephile.networking.response.Movie

class MoviePagingSource(private val apiService: APIService): PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
       return state.anchorPosition?.let { anchor ->
            state.closestPageToPosition(anchor)?.prevKey?.plus(1) ?:
            state.closestPageToPosition(anchor)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: 1

        return try {
            val results = apiService.getMovies(page)
            Log.i("MovieListScreen", "Fetched results ${results.results.size}")
            LoadResult.Page(
                data = results.results,
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