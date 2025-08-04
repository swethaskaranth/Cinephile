package com.kaizencoder.cinephile.presentation.moviedetail.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.kaizencoder.cinephile.R
import com.kaizencoder.cinephile.domain.model.CastMember
import com.kaizencoder.cinephile.presentation.theme.CinephileTheme

@Composable
fun CastItem(cast: CastMember) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        val posterPath = cast.profilePath ?: ""
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
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun CastItemPreview() {
    CinephileTheme {
        Surface {
            CastItem(
                CastMember(
                    1, "", "", 1, ""
                )
            )
        }
    }

}