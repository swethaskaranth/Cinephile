package com.kaizencoder.cinephile.presentation.moviedetail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kaizencoder.cinephile.domain.model.CastMember

@Composable
fun CastSection(cast: List<CastMember>) {
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
