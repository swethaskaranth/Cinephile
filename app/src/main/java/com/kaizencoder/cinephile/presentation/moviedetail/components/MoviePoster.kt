package com.kaizencoder.cinephile.presentation.moviedetail.components

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.kaizencoder.cinephile.R

@Composable
fun MoviePoster(posterPath: String, title: String, modifier: Modifier = Modifier) {
    AsyncImage(
        model = "https://image.tmdb.org/t/p/w500$posterPath",
        contentDescription = "Poster of $title",
        contentScale = ContentScale.Fit,
        modifier = modifier
            .aspectRatio(ratio = 2f / 3f)
            .clip(
                RoundedCornerShape(
                    topEnd = 24.dp,
                    bottomEnd = 24.dp,
                    bottomStart = 24.dp
                )
            ),
        placeholder = painterResource(R.drawable.placeholder_movie_poster),
        error = painterResource(R.drawable.error_movie_poster)
    )
}
