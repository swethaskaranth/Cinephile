package com.kaizencoder.cinephile.presentation.home.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.kaizencoder.cinephile.domain.model.Movie
import com.kaizencoder.cinephile.presentation.common.components.MovieItem
import com.kaizencoder.cinephile.presentation.common.effects.shimmerAnimationEffect

@Composable
fun MovieSection(
    title: String,
    movies: LazyPagingItems<Movie>,
    onTitleClick : () -> Unit,
    onCardClick: (Int) -> Unit,) {

    Column(
        modifier = Modifier.background(MaterialTheme.colorScheme.surfaceContainer)
    ) {

        Row(horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 16.dp, top = 12.dp)
                .clickable(enabled = true, onClick = onTitleClick)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
            )
            Text(text = ">",style = MaterialTheme.typography.titleLarge)
        }


        when (movies.loadState.refresh) {
            is LoadState.Loading -> {
                Log.i("ShimmerEffect", "Now in loading state")
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    items(count = 5) {
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
                        movie?.let { MovieItem(it, onCardClick) }
                    }
                }
            }

        }
    }
}

