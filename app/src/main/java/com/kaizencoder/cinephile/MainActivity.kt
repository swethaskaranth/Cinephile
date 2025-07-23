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
import com.kaizencoder.cinephile.ui.components.AppLogo
import com.kaizencoder.cinephile.ui.screens.MovieHomeScreen
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
                Scaffold(modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = {
                                AppLogo()
                            },
                            actions = {
                                Icon(Icons.Default.Search, contentDescription = "Search",
                                    modifier = Modifier.padding(horizontal = 16.dp))
                            }
                        )
                    }) { innerPadding ->
                    MovieHomeScreen(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

