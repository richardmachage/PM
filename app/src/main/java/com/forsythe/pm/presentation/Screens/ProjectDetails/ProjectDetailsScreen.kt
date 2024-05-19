package com.forsythe.pm.presentation.Screens.ProjectDetails

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import com.forsythe.pm.presentation.ui.theme.PMTheme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun ProjectDetailsScreen(
    navigator: DestinationsNavigator,
    projectId: String,
    name: String,
    createdAt: String,
    description: String

) {
    val viewModel: ProjectDetailsViewModel = hiltViewModel()
    val context = LocalContext.current
    val tasks = listOf(
        "Create homepage design",
        "Create about page design",
        "Create contact page design"
    )

    var launchKey by remember {
        mutableStateOf(0)
    }

    LaunchedEffect(launchKey) {
        viewModel.fetchProjectDetails(projectId = projectId)
    }

    LaunchedEffect(viewModel.toastMessage.value) {
        if (viewModel.toastMessage.value.isNotBlank()) {
            Toast.makeText(context, viewModel.toastMessage.value, Toast.LENGTH_SHORT).show()
            viewModel.toastMessage.value = ""
        }
    }
    PMTheme {
        launchKey + 1
        Scaffold(
            topBar = {
                TopAppBar(title = { Text(text = "Project Details") },
                    navigationIcon = {
                        IconButton(onClick = {
                            navigator.navigateUp()
                        }) {
                            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                        }
                    })
            }
        ) {

            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Text(
                        text = name,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    Text(
                        text = description,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    Text(
                        text = "Created on $createdAt",
                        fontSize = 14.sp,
                        //color = Color.Gray,
                        modifier = Modifier.padding(bottom = 2.dp)
                    )
                    /*Text(
                        text = "created By John",
                        fontSize = 14.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )*/
                    Text(
                        text = "Associated tasks (${tasks.size})",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    tasks.forEach { task ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = false,
                                onCheckedChange = { /* TODO */ },
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = task,
                                fontSize = 16.sp
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                    Button(
                        onClick = { /* TODO */ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 10.dp),
                        //colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray)
                    ) {
                        Text("Update project")
                    }
                    TextButton(
                        onClick = { /* TODO */ },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Archive project")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProjectDetailsPrev() {
    //ProjectDetailsScreen( )
}