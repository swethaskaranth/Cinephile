package com.kaizencoder.cinephile.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.kaizencoder.cinephile.model.MovieCategory
import com.kaizencoder.cinephile.ui.MovieViewModel
import com.kaizencoder.cinephile.ui.components.AppLogo
import com.kaizencoder.cinephile.ui.components.MovieSection

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieHomeScreen(
    onNavigateToCategory: (MovieCategory) -> Unit,
    onCardClick: (Int) -> Unit,
    movieViewModel: MovieViewModel = hiltViewModel()
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
        val popularMovies = movieViewModel.popularMovies.collectAsLazyPagingItems()
        val nowPlayingMovies = movieViewModel.nowPlayingMovies.collectAsLazyPagingItems()
        val topRatedMovies = movieViewModel.topRatedMovies.collectAsLazyPagingItems()
        val upcomingMovies = movieViewModel.upcomingMovies.collectAsLazyPagingItems()

        LaunchedEffect(true) {
            Log.i("MovieListScreen", "Inside Movie List Screen composable")
        }

        LazyColumn(
            Modifier.padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                MovieSection(
                    "Popular Movies",
                    popularMovies,
                    { onNavigateToCategory(MovieCategory.POPULAR) },
                    onCardClick
                )
            }
            item {
                MovieSection(
                    "Now Playing",
                    nowPlayingMovies,
                    { onNavigateToCategory(MovieCategory.NOW_PLAYING) },
                    onCardClick
                )
            }
            item {
                MovieSection(
                    "Top Rated Movies",
                    topRatedMovies,
                    { onNavigateToCategory(MovieCategory.TOP_RATED) },
                    onCardClick
                )
            }
            item {
                MovieSection(
                    "Upcoming Movies",
                    upcomingMovies, { onNavigateToCategory(MovieCategory.UPCOMING) },
                    onCardClick
                )
            }
        }
    }


}