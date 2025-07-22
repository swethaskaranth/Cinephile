package com.kaizencoder.cinephile.ui.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.kaizencoder.cinephile.networking.response.Movie
import com.kaizencoder.cinephile.ui.utils.shimmerAnimationEffect

@Composable
fun MovieSection(title: String, movies: LazyPagingItems<Movie>) {

    Column(
        modifier = Modifier.background(MaterialTheme.colorScheme.surfaceContainer)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(start = 16.dp, top = 16.dp)
        )

        when (movies.loadState.refresh) {
            is LoadState.Loading -> {
                Log.i("ShimmerEffect", "Now in loading state")
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    items(5) {
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
                Log.i("ShimmerEffect", "Now in else of ${movies.loadState.append} state")
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
    }
}

