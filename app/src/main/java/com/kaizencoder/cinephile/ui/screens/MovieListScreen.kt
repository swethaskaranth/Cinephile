package com.kaizencoder.cinephile.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.kaizencoder.cinephile.model.MovieCategory
import com.kaizencoder.cinephile.model.MovieCategory.*
import com.kaizencoder.cinephile.ui.MovieViewModel
import com.kaizencoder.cinephile.ui.components.MovieItem
import com.kaizencoder.cinephile.ui.utils.shimmerAnimationEffect

@Composable
fun MovieListScreen(
    category: MovieCategory,
    modifier: Modifier = Modifier,
    viewModel: MovieViewModel = hiltViewModel()
) {

    val movies = when (category) {
        POPULAR -> viewModel.popularMovies.collectAsLazyPagingItems()
        NOW_PLAYING -> viewModel.nowPlayingMovies.collectAsLazyPagingItems()
        TOP_RATED -> viewModel.topRatedMovies.collectAsLazyPagingItems()
        UPCOMING -> viewModel.upcomingMovies.collectAsLazyPagingItems()
    }

    when (movies.loadState.refresh) {
        is LoadState.Loading -> {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(16.dp),
                modifier = modifier
            ) {
                items(10) {
                    Box(
                        modifier = Modifier
                            .size(160.dp, 326.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .shimmerAnimationEffect()
                    )
                }
            }
        }

        is LoadState.Error -> {
            Log.i("ShimmerEffect", "Error state")
            val e = movies.loadState.refresh as LoadState.Error
            Text(text = "Error: ${e.error.localizedMessage}")
        }
        else -> {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(16.dp),
                modifier = modifier
            ) {
                items(movies.itemCount) { index ->
                    val movie = movies[index];
                    movie?.let {
                        MovieItem(it)
                    }
                }
            }
        }
    }




}