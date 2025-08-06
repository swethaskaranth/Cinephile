package com.kaizencoder.cinephile.presentation.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kaizencoder.cinephile.R

@Composable
fun AppLogo() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .background(Color.Yellow)
            .padding(4.dp)
    ) {

        Text(
            text = buildAnnotatedString {
                val appName = stringResource(R.string.app_name)
                withStyle(style = SpanStyle(color = Color.Black)) {
                    append(appName.substring(startIndex = 0, endIndex = 4))
                }
                withStyle(style = SpanStyle(color = Color(red = 6, green = 84, blue = 43))) {
                    append(appName.substring(startIndex = 4, endIndex = appName.length))
                }
            },
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
    }
}


@Preview
@Composable
private fun AppLogoPreview() {
    AppLogo()
}
