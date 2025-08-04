package com.kaizencoder.cinephile.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.kaizencoder.cinephile.presentation.home.MovieHomeScreen
import com.kaizencoder.cinephile.presentation.moviedetail.MovieDetailScreen
import com.kaizencoder.cinephile.presentation.moviedetail.MovieDetailViewModel
import com.kaizencoder.cinephile.presentation.movielist.MovieListScreen
import com.kaizencoder.cinephile.presentation.common.navigation.Route
import com.kaizencoder.cinephile.presentation.ui.theme.CinephileTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CinephileTheme {

                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Route.HomeScreen
                ) {
                    composable<Route.HomeScreen> {
                        MovieHomeScreen(
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
                            onClick = { id ->
                                navController.navigate(Route.MovieDetailScreen(id))

                            })
                    }
                    composable<Route.MovieDetailScreen> { backStackEntry ->
                        val detailViewModel: MovieDetailViewModel = hiltViewModel()
                        MovieDetailScreen(
                            detailViewModel,
                        )
                    }
                }

            }
        }
    }
}