package com.forsythe.pm.presentation.Screens.LogInScreen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.pm.ShortcutInfoCompat.Surface
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.forsythe.pm.presentation.others.MyCircularProgressBar

@Composable
fun LoginScreen(
    navController: NavController
) {
    val viewModel : LoginViewModel = hiltViewModel()
    val context = LocalContext.current

    LaunchedEffect (viewModel.toastMessage.value){
        val  message = viewModel.toastMessage.value
        if (message.isNotBlank()){
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            viewModel.toastMessage.value = ""
        }
    }

    LaunchedEffect (viewModel.performNavigation.value){
        if (viewModel.performNavigation.value.isNotBlank()){
            navController.navigate(route = "home_screen")
            viewModel.performNavigation.value = ""
        }
    }

    Surface {
        MyCircularProgressBar(isLoading = viewModel.isLoading.value)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Log in to your account",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(32.dp))

            BasicTextField( // Input Username
                value = viewModel.usernameOrEmail.value,
                onValueChange = { viewModel.usernameOrEmail.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .background(Color(0xFFF0F0F0), shape = MaterialTheme.shapes.small)
                    .padding(16.dp),
                singleLine = true,
                decorationBox = { innerTextField ->
                    if (viewModel.usernameOrEmail.value.isEmpty()) {
                        Text(text = "username or email", color = Color.Gray)
                    }
                    innerTextField()
                }
            )

            Spacer(modifier = Modifier.height(16.dp))


            BasicTextField( // input Password
                value = viewModel.password.value,
                onValueChange = { viewModel.password.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .background(Color(0xFFF0F0F0), shape = MaterialTheme.shapes.small)
                    .padding(16.dp),
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                decorationBox = { innerTextField ->
                    if (viewModel.password.value.isEmpty()) {
                        Text(text = "Password", color = Color.Gray)
                    }
                    innerTextField()
                }
            )



            TextButton( //Forgotten Password
                onClick = {
            }, modifier = Modifier
                .align(Alignment.End)
                )
            {
                Text(
                    text = "Forgot Password?",
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                //Log in button
                onClick = {
                          viewModel.onLogIn()
                          },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Text(text = "Log in")
            }

            Spacer(modifier = Modifier.height(32.dp))

            TextButton(onClick = {
                navController.navigate(route = "signup_screen")
            }) {
                Text(
                    text = "New user? Sign Up",
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

        }
    }

}
