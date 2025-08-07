package com.kaizencoder.cinephile.presentation.moviedetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kaizencoder.cinephile.domain.model.CrewMember

@Composable
fun WritersSection(writers: List<CrewMember>) {
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
                    color = Color(red = 87, green = 153, blue = 239),
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
