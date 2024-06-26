package com.forsythe.pm.presentation.Screens.NewProjectScreen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.forsythe.pm.presentation.others.MyCircularProgressBar
import com.forsythe.pm.presentation.ui.theme.PMTheme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun CreateProjectScreen(
    navigator: DestinationsNavigator
) {
    val viewModel :NewProjectViewModel = hiltViewModel()
    val context = LocalContext.current

    LaunchedEffect(viewModel.toastMessage.value) {
        val message = viewModel.toastMessage.value
        if (message.isNotBlank()){
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }
    Scaffold(
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "New Project",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                    /* handle close click */
                       navigator.navigateUp()
                    }) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close"
                        )
                    }
                }
            )
        },
        content = { padding ->
            MyCircularProgressBar(isLoading = viewModel.isLoading.value)
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Project Name",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.Start)
                )

                Spacer(modifier = Modifier.height(8.dp))

                MyBasicBigInputTextField(
                    value = viewModel.projectName.value,
                    onValueChange = { viewModel.projectName.value = it },
                    placeholder = "Project name"
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Description",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.Start)
                )

                Spacer(modifier = Modifier.height(8.dp))

                MyBasicBigInputTextField(
                    value = viewModel.projectDescription.value,
                    onValueChange = { viewModel.projectDescription.value = it },
                    placeholder = "Project description",
                    singleLine = false,
                    maxLines = 5
                )

                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    onClick = { /* handle create project click */
                              viewModel.createProject()
                              },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                   // colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary )//Color(0xFF4285F4))
                ) {
                    Text(text = "Create project")
                }
            }
        }
    )
}

@Composable
fun MyBasicBigInputTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    singleLine: Boolean = true,
    maxLines: Int = 1
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color(0xFFF0F0F0), shape = MaterialTheme.shapes.small)
            .padding(16.dp),
        singleLine = singleLine,
        maxLines = maxLines,
        decorationBox = { innerTextField ->
            if (value.isEmpty()) {
                Text(text = placeholder, color = Color.Gray)
            }
            innerTextField()
        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CreateProjectPreview(){
    PMTheme {
        //CreateProjectScreen(rememberNavController())
    }
}