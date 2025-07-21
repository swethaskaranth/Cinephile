package com.kaizencoder.cinephile.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.ImageLoader
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.util.DebugLogger
import com.kaizencoder.cinephile.Constants
import com.kaizencoder.cinephile.networking.response.Movie

@Composable
fun MovieItem(movie: Movie) {
    Card(
        modifier = Modifier
            .width(160.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {

        LaunchedEffect(true) {
            Log.i("MovieListScreen","Movie poster ${Constants.IMAGE_BASE_URL + movie.poster_path}")
        }
        Column {
        /*    Image(
                painter = painterResource(R.drawable.test_image),
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(2f / 3f)
                    .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)),
                contentScale = ContentScale.Fit,
                contentDescription = "movie_poster"
            )*/

            val context = LocalContext.current
            val posterPath = movie.poster_path ?: ""
            val imageUrl = "https://image.tmdb.org/t/p/w500$posterPath"

            Log.d("ImageType", "Type: ${imageUrl::class.simpleName}, Value: $imageUrl")

            val imageLoader = ImageLoader.Builder(LocalContext.current)
                .logger(DebugLogger()) // Add this
                .build()
            AsyncImage(model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),
                imageLoader = imageLoader,
                contentDescription = "movie_poster",
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(2f / 3f)
                    .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)),
                contentScale = ContentScale.Fit,)

            Column(modifier = Modifier.height(72.dp).padding(horizontal = 12.dp, vertical = 8.dp)) {
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

