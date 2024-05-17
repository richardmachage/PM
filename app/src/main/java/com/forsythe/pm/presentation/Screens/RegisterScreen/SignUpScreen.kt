package com.forsythe.pm.presentation.Screens.RegisterScreen

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.forsythe.pm.presentation.others.MyBasicInputTextField
import com.forsythe.pm.presentation.ui.theme.PMTheme


@Composable
fun SignUpScreen(navController: NavController) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Surface {


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
                    navController.navigateUp()
                },
                modifier = Modifier.align(Alignment.Start). padding(top = 10.dp)
            ) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "")
            }
            /* Text(
            text = "←",
            modifier = Modifier
                .align(Alignment.Start)
                .clickable { *//* handle back click *//* },
            fontSize = 24.sp
        )*/

            Spacer(modifier = Modifier.height(32.dp))

            MyBasicInputTextField(
                value = username,
                onValueChange = { username = it },
                placeholder = "Username"
            )

            Spacer(modifier = Modifier.height(16.dp))

            MyBasicInputTextField(
                value = email,
                onValueChange = { email = it },
                placeholder = "Email"
            )

            Spacer(modifier = Modifier.height(16.dp))

            MyBasicInputTextField(
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                placeholder = "Phone number"
            )

            Spacer(modifier = Modifier.height(16.dp))

            MyBasicInputTextField(
                value = password,
                onValueChange = { password = it },
                placeholder = "Password",
                isPassword = true
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = { /* handle sign up click */ },
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
        SignUpScreen(rememberNavController())
    }
}