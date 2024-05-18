package com.forsythe.pm.presentation.others

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun MyCircularProgressBar(
    isDisplayed: Boolean,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary,
    strokeWidth: Dp = 4.dp
) {
    if (isDisplayed) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier.fillMaxSize()
        ) {
            CircularProgressIndicator(
                color = color,
                strokeWidth = strokeWidth
            )
        }
    }
}
