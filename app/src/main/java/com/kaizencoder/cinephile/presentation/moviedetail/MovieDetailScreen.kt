package com.kaizencoder.cinephile.presentation.moviedetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kaizencoder.cinephile.domain.model.MovieDetail
import com.kaizencoder.cinephile.presentation.moviedetail.components.CastSection
import com.kaizencoder.cinephile.presentation.moviedetail.components.ExpandableOverview
import com.kaizencoder.cinephile.presentation.moviedetail.components.GenreChip
import com.kaizencoder.cinephile.presentation.moviedetail.components.LoadingState
import com.kaizencoder.cinephile.presentation.moviedetail.components.MoviePoster
import com.kaizencoder.cinephile.presentation.moviedetail.components.MovieRating

@Composable
fun MovieDetailScreen(
    movieDetailViewModel: MovieDetailViewModel,
    modifier: Modifier = Modifier
) {

    val movieDetailUiState by movieDetailViewModel.movieDetailUiState
    val creditsUiState by movieDetailViewModel.creditsUiState

    Surface(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        if (movieDetailUiState.isLoading)
            LoadingState(modifier)

        if (!movieDetailUiState.error.isNullOrBlank())
            Text(text = movieDetailUiState.error ?: "Unknown error occurred.")

        movieDetailUiState.movieDetail?.let { movie ->
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
                    MovieRating(movie.rating, movie.totalVotes)
                }

                item(key = "credits") {
                    MovieCredits(creditsUiState)
                }
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
    val posterPath = movie.posterPath ?: ""

    Row {
        MoviePoster(posterPath, movie.title, Modifier.weight(weight = 0.7f))

        Spacer(Modifier.width(16.dp))

        MovieInfo(movie.genres, movie.overview, Modifier.weight(weight = 1f))
    }
}

@Composable
fun MovieInfo(genres: List<String>, overview: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {

        GenreChips(genres)

        ExpandableOverview(overview, Modifier.padding(top = 12.dp))

    }
}

@Composable
fun GenreChips(genres: List<String>) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(genres) { genre ->
            GenreChip(genre)
        }
    }
}

@Composable
fun MovieCredits(creditsUiState: CreditsUiState) {
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
            color = Color(red = 87, green = 153, blue = 239),
            modifier = Modifier.padding(start = 12.dp)
        )
    }
}
