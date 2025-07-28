package com.kaizencoder.cinephile.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.compose.ui.res.painterResource
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
import com.kaizencoder.cinephile.R
import com.kaizencoder.cinephile.networking.response.Cast
import com.kaizencoder.cinephile.networking.response.Crew
import com.kaizencoder.cinephile.networking.response.Genre
import com.kaizencoder.cinephile.networking.response.MovieDetail
import com.kaizencoder.cinephile.ui.MovieDetailViewModel
import com.kaizencoder.cinephile.ui.components.CastItem
import java.util.Locale

@Composable
fun MovieDetailScreen(
    movieDetailViewModel: MovieDetailViewModel,
    modifier: Modifier = Modifier
) {

    val movieDetail by movieDetailViewModel.movie.collectAsStateWithLifecycle(null)
    val creditsUiState by movieDetailViewModel.creditsUiState.collectAsStateWithLifecycle()

    Surface(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        movieDetail?.let { movie ->
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = modifier
                    .padding(vertical = 16.dp, horizontal = 16.dp)
            ) {

                item(key = "title") {
                    MovieTitle(movie.title)
                }
                item(key = "poster_overview") {
                    MoviePosterAndOverview(movie)
                }

                item(key = "rating") {
                    MovieRating(movie.vote_average, movie.vote_count)
                }

                item(key = "credits") {
                    MovieCredits(creditsUiState)
                }
            }
        } ?: run {
            LoadingState(modifier)
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

        Spacer(Modifier.width(16.dp))

        MovieInfo(movie.genres, movie.overview, Modifier.weight(1f))
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
            ),
        placeholder = painterResource(R.drawable.placeholder_movie_poster),
        error = painterResource(R.drawable.error_movie_poster)
    )
}

@Composable
fun MovieInfo(genres: List<Genre>, overview: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {

        GenreChips(genres)

        ExpandableOverview(overview, Modifier.padding(top = 12.dp))

    }
}

@Composable
fun GenreChips(genres: List<Genre>) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(genres, key = { it.id }) { genre ->
            GenreChip(genre.name)
        }
    }
}

@Composable
fun GenreChip(name: String) {
    Surface(
        shape = MaterialTheme.shapes.large,
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
        ),
        color = Color.Transparent
    ) {
        Text(
            text = name,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
            style = MaterialTheme.typography.labelMedium
        )
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
    voteCount: Int
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            imageVector = Icons.Default.Star,
            contentDescription = "rating",
            tint = Color(0xFFFFD700),
            modifier = Modifier.size(20.dp)
        )

        Text(
            text = buildAnnotatedString {
                withStyle(SpanStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp)) {
                    append(String.format(Locale.US, "%.1f", voteAverage))
                }
                withStyle(
                    SpanStyle(
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
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
            text = formatVoteCount(voteCount),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
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
fun CreditRow(label: String, name: String) {
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
fun WritersSection(writers: List<Crew>) {
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
fun CastSection(cast: List<Cast>) {
    Text(
        text = "Top cast",
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
    )

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(cast, key = { it.id }) {
            CastItem(it)
        }
    }
}

@Composable
fun LoadingState(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

fun formatVoteCount(voteCount: Int): String = if (voteCount > 1000) "${voteCount / 1000}K" else voteCount.toString()