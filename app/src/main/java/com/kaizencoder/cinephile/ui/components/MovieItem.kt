package com.kaizencoder.cinephile.ui.components

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.ImageLoader
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.util.DebugLogger
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.kaizencoder.cinephile.Constants
import com.kaizencoder.cinephile.R
import com.kaizencoder.cinephile.networking.response.Movie

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MovieItem(movie: Movie, onClick: (Int) -> Unit) {
    Card(
        modifier = Modifier
            .width(160.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        onClick = { onClick(movie.id) }
    ) {

        LaunchedEffect(true) {
            Log.i("MovieListScreen", "Movie poster ${Constants.IMAGE_BASE_URL + movie.poster_path}")
        }
        Column {

            val context = LocalContext.current
            val posterPath = movie.poster_path ?: ""
            val imageUrl = "https://image.tmdb.org/t/p/w500$posterPath"

            Log.d("ImageType", "Type: ${imageUrl::class.simpleName}, Value: $imageUrl")

            val imageLoader = ImageLoader.Builder(LocalContext.current)
                .logger(DebugLogger()) // Add this
                .build()

            Box(Modifier.padding(bottom = 20.dp)) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(imageUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = "movie_poster",
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(2f / 3f)
                        .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)),
                    contentScale = ContentScale.Fit,
                    placeholder = painterResource(R.drawable.placeholder_movie_poster)
                )
                    Rating(movie.vote_average, Modifier.align(Alignment.BottomStart)
                        .offset(y=20.dp)
                        .padding(start = 12.dp))


            }

            Column(
                modifier = Modifier
                    .height(72.dp)
                    .padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = movie.release_date,
                    style = MaterialTheme.typography.bodySmall,
                )
            }

        }

    }
}

