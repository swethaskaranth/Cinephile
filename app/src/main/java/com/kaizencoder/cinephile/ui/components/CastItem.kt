package com.kaizencoder.cinephile.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.kaizencoder.cinephile.R
import com.kaizencoder.cinephile.networking.response.Cast
import com.kaizencoder.cinephile.ui.theme.CinephileTheme

@Composable
fun CastItem(cast: Cast) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        val posterPath = cast.profile_path ?: ""
        AsyncImage(
            model = "https://image.tmdb.org/t/p/w500$posterPath",
            contentDescription = "csat photo",
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop,
            placeholder = painterResource(R.drawable.cast_image_placeholder),
            error = painterResource(R.drawable.cast_image_placeholder)
        )
        Text(
            text = cast.name,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            modifier = Modifier.padding(top = 10.dp)
        )
        Text(
            text = cast.character ?: "",
            fontSize = 12.sp,
            lineHeight = 14.sp
        )
    }
}

@Preview(
    showSystemUi = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun CastItemPreview() {
    CinephileTheme {
        Surface {
            CastItem(
                Cast(
                    true,
                    1,
                    "Superman",
                    "1",
                    1,
                    1,
                    "Acting",
                    "David Corenswet",
                    0,
                    "David Corsenwet",
                    1.0,
                    ""
                )
            )
        }
    }

}