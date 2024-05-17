package com.forsythe.pm.presentation.others

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.forsythe.pm.presentation.ui.theme.PMTheme

@Composable
fun FloatingAddButton(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = onClick,
        modifier = Modifier.padding(),
        containerColor = MaterialTheme.colorScheme.primary//Color(0xFF4285F4)
    ) {
        //Text(text = "+", fontSize = 24.sp, color = androidx.compose.ui.graphics.Color.White)
        Icon(imageVector = Icons.Default.Add, contentDescription = "add", tint = Color.White)
    }
}

@Preview(showBackground = false, showSystemUi = false)
@Composable
fun SignUpPreview(){
    PMTheme {
        FloatingAddButton(onClick = {})
    }
}