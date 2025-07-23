package com.kaizencoder.cinephile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.kaizencoder.cinephile.ui.MovieDetailViewModel
import com.kaizencoder.cinephile.ui.Route
import com.kaizencoder.cinephile.ui.components.AppLogo
import com.kaizencoder.cinephile.ui.screens.MovieDetailScreen
import com.kaizencoder.cinephile.ui.screens.MovieHomeScreen
import com.kaizencoder.cinephile.ui.screens.MovieListScreen
import com.kaizencoder.cinephile.ui.theme.CinephileTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CinephileTheme {
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
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Route.HomeScreen
                    ) {
                        composable<Route.HomeScreen> {
                            MovieHomeScreen(
                                Modifier.padding(innerPadding),
                                onNavigateToCategory = { category ->
                                    navController.navigate(Route.MovieListingScreen(category))
                                },
                                onCardClick = { id ->
                                    navController.navigate(Route.MovieDetailScreen(id))

                                })
                        }
                        composable<Route.MovieListingScreen> { backStackEntry ->
                            val details: Route.MovieListingScreen = backStackEntry.toRoute()
                            MovieListScreen(
                                details.category,
                                Modifier.padding(innerPadding),
                                onClick = { id ->
                                    navController.navigate(Route.MovieDetailScreen(id))

                                })
                        }
                        composable<Route.MovieDetailScreen> { backStackEntry ->
                            val details: Route.MovieDetailScreen = backStackEntry.toRoute()
                            val detailViewModel: MovieDetailViewModel = hiltViewModel()
                            MovieDetailScreen(
                                detailViewModel,
                                Modifier.padding(innerPadding)
                            )
                        }
                    }

                }
            }
        }
    }
}

