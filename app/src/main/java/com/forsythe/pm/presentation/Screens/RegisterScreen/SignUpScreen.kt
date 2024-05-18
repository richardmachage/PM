package com.forsythe.pm.presentation.Screens.RegisterScreen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.forsythe.pm.presentation.others.MyBasicInputTextField
import com.forsythe.pm.presentation.others.MyCircularProgressBar
import com.forsythe.pm.presentation.ui.theme.PMTheme
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun SignUpScreen(
) {
    val viewModel : SignUpViewModel = hiltViewModel()
    val context = LocalContext.current

    LaunchedEffect(viewModel.toastMessage.value) {
        val message = viewModel.toastMessage.value
        if (message.isNotBlank()){
            Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
            viewModel.toastMessage.value = ""
        }
    }
    Surface {
        MyCircularProgressBar(isLoading = viewModel.isLoading.value)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            //verticalArrangement = Arrangement.Center
        ) {
            // Back Arrow
            IconButton(
                onClick = {
                    //handle back click
                    //navController.navigateUp()
                },
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(top = 10.dp)
            ) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "")
            }

            Spacer(modifier = Modifier.height(10.dp))

            MyBasicInputTextField(
                value = viewModel.username.value,
                onValueChange = { viewModel.username.value = it },
                placeholder = "Username"
            )

            Spacer(modifier = Modifier.height(10.dp))

            MyBasicInputTextField(
                value = viewModel.email.value,
                onValueChange = { viewModel.email.value = it },
                placeholder = "Email"
            )

            Spacer(modifier = Modifier.height(10.dp))

            MyBasicInputTextField(
                value = viewModel.phoneNumber.value,
                onValueChange = { viewModel.phoneNumber.value = it },
                placeholder = "Phone number"
            )

            Spacer(modifier = Modifier.height(16.dp))

            MyBasicInputTextField(
                value = viewModel.password.value,
                onValueChange = { viewModel.password.value = it },
                placeholder = "Password",
                isPassword = true
            )

            Spacer(modifier = Modifier.height(10.dp))

            MyBasicInputTextField(
                value = viewModel.confirmPassword.value,
                onValueChange = { viewModel.confirmPassword.value = it },
                placeholder = "Confirm password",
                isPassword = true
            )

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = { /* handle sign up click */
                          viewModel.onSignUp()
                          },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Text(text = "Sign Up")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "By clicking Sign Up, you agree to our Terms, Data Policy and Cookie Policy.",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 12.sp,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SignUpPreview() {
    PMTheme {
       // SignUpScreen(rememberNavController())
    }
}