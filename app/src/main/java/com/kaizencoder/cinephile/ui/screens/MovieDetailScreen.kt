package com.kaizencoder.cinephile.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.kaizencoder.cinephile.ui.MovieDetailViewModel

@Composable
fun MovieDetailScreen(
    movieDetailViewModel: MovieDetailViewModel,
    modifier: Modifier = Modifier
) {

    val movie = movieDetailViewModel.movie.collectAsStateWithLifecycle(null).value

    if (movie != null) {
        LazyColumn(
            modifier
                .padding(horizontal = 16.dp)
        ) {
            val posterPath = movie.poster_path ?: ""
            item {
                Row {
                    AsyncImage(
                        model = "https://image.tmdb.org/t/p/w500$posterPath",
                        contentDescription = "Movie Poster",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .weight(0.7f)
                            .aspectRatio(2f / 3f)
                            .clip(
                                RoundedCornerShape(
                                    topEnd = 24.dp,
                                    bottomEnd = 24.dp,
                                    bottomStart = 24.dp
                                )
                            )
                    )

                    Spacer(Modifier.width(10.dp))

                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        var isExpanded by remember { mutableStateOf(false) }
                        var isTextOverflowing by remember { mutableStateOf(false) }
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(movie.genres) { genre ->
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier
                                        .border(
                                            1.dp, MaterialTheme.colorScheme.onSecondaryContainer,
                                            MaterialTheme.shapes.large
                                        )
                                        .padding(horizontal = 10.dp, vertical = 4.dp)
                                ) {
                                    Text(text = genre.name)
                                }
                            }
                        }
                        Text(
                            text = movie.overview,
                            modifier = Modifier.padding(top = 12.dp),
                            style = MaterialTheme.typography.bodyMedium,
                            maxLines = if (isExpanded) Int.MAX_VALUE else 10,
                            overflow = TextOverflow.Ellipsis,
                            onTextLayout = { layoutResult ->
                                if (!isExpanded) {
                                    isTextOverflowing = layoutResult.hasVisualOverflow
                                }
                            }
                        )
                        if (isTextOverflowing || isExpanded)
                            Text(
                                text = if (isExpanded) "Read Less" else "Read More",
                                color = Color.Blue,
                                modifier = Modifier
                                    .padding(top = 5.dp)
                                    .clickable(onClick = {
                                        isExpanded = !isExpanded
                                    })
                            )
                    }
                }
            }

        }
    }

}