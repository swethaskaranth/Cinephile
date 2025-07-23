package com.kaizencoder.cinephile.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.kaizencoder.cinephile.model.MovieCategory
import com.kaizencoder.cinephile.model.MovieCategory.*
import com.kaizencoder.cinephile.ui.MovieViewModel
import com.kaizencoder.cinephile.ui.components.MovieItem

@Composable
fun MovieListScreen(
    category: MovieCategory,
    modifier: Modifier = Modifier,
    viewModel: MovieViewModel = hiltViewModel()
) {

    val movies = when(category){
        POPULAR -> viewModel.popularMovies.collectAsLazyPagingItems()
        NOW_PLAYING -> viewModel.nowPlayingMovies.collectAsLazyPagingItems()
        TOP_RATED -> viewModel.topRatedMovies.collectAsLazyPagingItems()
        UPCOMING -> viewModel.upcomingMovies.collectAsLazyPagingItems()
    }

    LazyVerticalGrid(columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(movies.itemCount) { index ->
        val movie = movies[index];
        movie?.let {
            MovieItem(it)
        }}
    }



}