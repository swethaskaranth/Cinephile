package com.kaizencoder.cinephile.presentation.movielist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.kaizencoder.cinephile.domain.model.Movie
import com.kaizencoder.cinephile.domain.model.MovieCategory
import com.kaizencoder.cinephile.domain.model.MovieCategory.NOW_PLAYING
import com.kaizencoder.cinephile.domain.model.MovieCategory.POPULAR
import com.kaizencoder.cinephile.domain.model.MovieCategory.TOP_RATED
import com.kaizencoder.cinephile.domain.model.MovieCategory.UPCOMING
import com.kaizencoder.cinephile.presentation.common.components.AppLogo
import com.kaizencoder.cinephile.presentation.common.components.MovieItem
import com.kaizencoder.cinephile.presentation.common.effects.shimmerAnimationEffect

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieListScreen(
    category: MovieCategory,
    onClick: (Int) -> Unit,
    viewModel: MovieViewModel = hiltViewModel()
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    AppLogo()
                },
                actions = {
                    Icon(
                        Icons.Default.Search, contentDescription = "Search",
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
            )
        }) { innerPadding ->
        val movies = when (category) {
            POPULAR -> viewModel.popularMovies.collectAsLazyPagingItems()
            NOW_PLAYING -> viewModel.nowPlayingMovies.collectAsLazyPagingItems()
            TOP_RATED -> viewModel.topRatedMovies.collectAsLazyPagingItems()
            UPCOMING -> viewModel.upcomingMovies.collectAsLazyPagingItems()
        }

        when (movies.loadState.refresh) {
            is LoadState.Loading -> {
                ShimmerView(Modifier.padding(innerPadding))
            }

            is LoadState.Error -> {
                val e = movies.loadState.refresh as LoadState.Error
                Text(text = "Error: ${e.error.localizedMessage}")
            }

            else -> {
                MovieGridView(movies, onClick, Modifier.padding(innerPadding))
            }
        }
    }
}

@Composable
fun ShimmerView(modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(16.dp),
        modifier = modifier
    ) {
        items(count = 10) {
            Box(
                modifier = Modifier
                    .size(160.dp, 326.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .shimmerAnimationEffect()
            )
        }
    }
}

@Composable
fun MovieGridView(movies: LazyPagingItems<Movie>,
                  onClick: (Int) -> Unit,
                  modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(16.dp),
        modifier = modifier
    ) {
        items(movies.itemCount) { index ->
            val movie = movies[index]
            movie?.let {
                MovieItem(it, onClick)
            }
        }
    }
}
