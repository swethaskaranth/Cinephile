package com.kaizencoder.cinephile.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.kaizencoder.cinephile.networking.response.Movie

@Composable
fun MovieSection(title: String, movies: LazyPagingItems<Movie>, modifier: Modifier = Modifier) {

        Column(
            modifier = Modifier.background(MaterialTheme.colorScheme.surfaceContainer)
        ) {
            Text(text = title,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(start = 16.dp, top = 16.dp))

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(movies.itemCount) { index ->
                    val movie = movies[index]
                    movie?.let { MovieItem(it) }
                }
            }
        }

}

