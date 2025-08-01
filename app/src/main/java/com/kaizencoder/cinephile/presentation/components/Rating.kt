package com.kaizencoder.cinephile.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Rating(rating: Double, modifier: Modifier) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(48.dp)
            .background(Color(0xFF0B1E2D), shape = CircleShape)
            .padding(3.dp)

    ) {
        CircularProgressIndicator(
            progress = { 1f },
            color = Color(0xFF193341),
            strokeWidth = 4.dp,
            strokeCap = StrokeCap.Butt
        )
        CircularProgressIndicator(
            progress = { (rating / 10f).toFloat() },
            color = Color(0xFF2BD97D),
            trackColor = Color(0xFF193341),
            strokeWidth = 4.dp,
            strokeCap = StrokeCap.Round
        )
        Text(
            text = "${(rating*10).toInt()}%",
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun RatingPreview() {
    Rating(8.14, Modifier)
}