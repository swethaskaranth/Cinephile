package com.kaizencoder.cinephile.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.kaizencoder.cinephile.networking.response.Cast
import com.kaizencoder.cinephile.networking.response.Crew
import com.kaizencoder.cinephile.networking.response.Genre
import com.kaizencoder.cinephile.networking.response.MovieDetail
import com.kaizencoder.cinephile.ui.MovieDetailViewModel
import com.kaizencoder.cinephile.ui.components.CastItem

@Composable
fun MovieDetailScreen(
    movieDetailViewModel: MovieDetailViewModel,
    modifier: Modifier = Modifier
) {

    val movie = movieDetailViewModel.movie.collectAsStateWithLifecycle(null).value
    val creditsUiState = movieDetailViewModel.creditsUiState.collectAsStateWithLifecycle().value

    if (movie != null) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
        ) {

            item {
                MovieTitle(movie.title)
            }
            item {
                MoviePosterAndOverview(movie)
            }

            item {
                MovieRating(movie.vote_average, movie.vote_count)
            }

            item {
                MovieCredits(creditsUiState)
            }
        }
    }
}

@Composable
fun MovieTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier.padding(top = 10.dp)
    )
}

@Composable
fun MoviePosterAndOverview(movie: MovieDetail) {
    val posterPath = movie.poster_path ?: ""

    Row {

        MoviePoster(posterPath, movie.title, Modifier.weight(0.7f))

        Spacer(Modifier.width(10.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {

            GenreChips(movie.genres)

            ExpandableOverview(movie.overview, Modifier.padding(top = 12.dp))

        }
    }
}

@Composable
fun MoviePoster(posterPath: String, title: String, modifier: Modifier = Modifier) {
    AsyncImage(
        model = "https://image.tmdb.org/t/p/w500$posterPath",
        contentDescription = "Poster of $title",
        contentScale = ContentScale.Fit,
        modifier = modifier
            .aspectRatio(2f / 3f)
            .clip(
                RoundedCornerShape(
                    topEnd = 24.dp,
                    bottomEnd = 24.dp,
                    bottomStart = 24.dp
                )
            )
    )
}

@Composable
fun GenreChips(genres: List<Genre>, modifier: Modifier = Modifier) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(genres) { genre ->
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
}

@Composable
fun ExpandableOverview(overview: String, modifier: Modifier = Modifier) {
    var isExpanded by remember { mutableStateOf(false) }
    var isTextOverflowing by remember { mutableStateOf(false) }
    Column(modifier = modifier) {
        Text(
            text = overview,
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
                color = Color(87, 153, 239),
                modifier = Modifier
                    .padding(top = 5.dp)
                    .clickable(onClick = {
                        isExpanded = !isExpanded
                    })
            )
    }
}

@Composable
fun MovieRating(
    voteAverage: Double,
    voteCount: Int,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            imageVector = Icons.Default.Star,
            contentDescription = "rating",
            tint = Color.Yellow,
            modifier = Modifier.size(20.dp)
        )

        Text(
            text = buildAnnotatedString {
                withStyle(SpanStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp)) {
                    append(String.format("%.1f", voteAverage))
                }
                withStyle(
                    SpanStyle(
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                        fontSize = 12.sp
                    )
                ) {
                    append("/10")
                }
            },
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier.alignByBaseline()
        )

        Text(
            text = if (voteCount > 1000) "${voteCount / 1000}K" else voteCount.toString(),
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.alignByBaseline()
        )
    }
}

@Composable
fun MovieCredits(creditsUiState: MovieDetailViewModel.CreditsUiState) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        HorizontalDivider(
            thickness = 1.dp
        )

        creditsUiState.directors.firstOrNull()?.let {
            CreditRow(label = "Director", name = it.name)

            HorizontalDivider(
                thickness = 1.dp
            )
        }


        if (creditsUiState.writers.isNotEmpty()) {
            WritersSection(creditsUiState.writers)
            HorizontalDivider(
                modifier = Modifier
                    .height(1.dp)
            )
        }

        CastSection(creditsUiState.cast)
    }
}

@Composable
fun CreditRow(label: String, name: String, modifier: Modifier = Modifier) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = label,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
        )

        Text(
            text = name,
            style = MaterialTheme.typography.titleMedium,
            color = Color(87, 153, 239),
            modifier = Modifier.padding(start = 12.dp)
        )
    }
}

@Composable
fun WritersSection(writers: List<Crew>, modifier: Modifier = Modifier) {
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Text(
            text = "Writers",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
        )

        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            writers.forEachIndexed { index, writer ->
                Text(
                    text = writer.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color(87, 153, 239),
                )

                if (index != writers.lastIndex) {
                    Text(
                        text = "·", color = Color.Gray,
                        fontSize = 20.sp
                    )
                }
            }
        }
    }
}

@Composable
fun CastSection(cast: List<Cast>, modifier: Modifier = Modifier) {
    Text(
        text = "Top cast",
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
    )

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(cast) {
            CastItem(it)
        }
    }
}