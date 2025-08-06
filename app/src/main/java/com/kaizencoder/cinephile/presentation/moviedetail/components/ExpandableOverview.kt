package com.kaizencoder.cinephile.presentation.moviedetail.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

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
                color = Color(red = 87, green = 153, blue = 239),
                modifier = Modifier
                    .padding(top = 5.dp)
                    .clickable(onClick = {
                        isExpanded = !isExpanded
                    })
            )
    }
}
