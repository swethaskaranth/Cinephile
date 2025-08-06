package com.kaizencoder.cinephile.presentation.moviedetail.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

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
