package com.kaizencoder.cinephile.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.kaizencoder.cinephile.ui.MovieViewModel

@Composable
fun MovieListScreen(
    modifier: Modifier = Modifier,
    movieViewModel: MovieViewModel = hiltViewModel()
) {

    val movies = movieViewModel.movies.collectAsLazyPagingItems()

    LaunchedEffect(true) {
        Log.i("MovieListScreen","Inside Movie List Screen composable")
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        items(movies.itemCount) { index ->
            val movie = movies[index]
            movie?.let { MovieItem(it) }
        }

    }
}