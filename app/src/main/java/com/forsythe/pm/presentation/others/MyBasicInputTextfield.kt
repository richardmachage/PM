package com.forsythe.pm.presentation.others

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.forsythe.pm.presentation.Screens.LogInScreen.LoginScreen
import com.forsythe.pm.presentation.Screens.SignUpScreen
import com.forsythe.pm.presentation.ui.theme.PMTheme

@Composable
fun MyBasicInputTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    isPassword: Boolean = false
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color(0xFFF0F0F0), shape = MaterialTheme.shapes.small)
            .padding(16.dp),
        singleLine = true,
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        decorationBox = { innerTextField ->
            if (value.isEmpty()) {
                Text(text = placeholder, color = Color.Gray)
            }
            innerTextField()
        }
    )
}


