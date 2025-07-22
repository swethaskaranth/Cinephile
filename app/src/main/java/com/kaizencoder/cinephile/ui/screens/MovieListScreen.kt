package com.kaizencoder.cinephile.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.kaizencoder.cinephile.ui.MovieViewModel
import com.kaizencoder.cinephile.ui.components.MovieSection

@Composable
fun MovieListScreen(
    modifier: Modifier = Modifier,
    movieViewModel: MovieViewModel = hiltViewModel()
) {

    val popularMovies = movieViewModel.popularMovies.collectAsLazyPagingItems()
    val nowPlayingMovies = movieViewModel.nowPlayingMovies.collectAsLazyPagingItems()
    val topRatedMovies = movieViewModel.topRatedMovies.collectAsLazyPagingItems()
    val upcomingMovies = movieViewModel.upcomingMovies.collectAsLazyPagingItems()

    LaunchedEffect(true) {
        Log.i("MovieListScreen", "Inside Movie List Screen composable")
    }

    LazyColumn(
        modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            MovieSection("Popular Movies", popularMovies)
        }
        item {
            MovieSection("Now Playing", nowPlayingMovies)
        }
        item {
            MovieSection("Top Rated Movies", topRatedMovies)
        }
        item {
            MovieSection("Upcoming Movies", upcomingMovies)
        }
    }
}