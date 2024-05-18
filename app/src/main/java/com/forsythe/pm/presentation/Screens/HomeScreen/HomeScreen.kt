package com.forsythe.pm.presentation.Screens.HomeScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.forsythe.pm.presentation.others.FloatingAddButton
import com.forsythe.pm.presentation.ui.theme.PMTheme
import com.ramcosta.composedestinations.annotation.Destination

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun HomeScreen() {
    val viewModel : HomeViewModel = hiltViewModel()
    val projects = listOf(
        Project("Product Launch 2023", "Due 2/28/23"),
        Project("Data Center Expansion", "Due 12/30/22"),
        Project("Q4 Sales Campaign", "Due 11/10/22"),
        Project("Board of Directors Meeting", "Due 1/20/23")
    )

    Scaffold(
        floatingActionButton = {
            FloatingAddButton(onClick = {
            /* handle add project click */
            //navController.navigate(route = "new_project_screen")
            })
        },
        topBar = {
            TopAppBar(
                title = { Text(text = "Home") },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        //got o account
                        Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "account")
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        //go to archived
                        Icon(imageVector = Icons.Filled.List, contentDescription = "Archive")
                    }

                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Text(
                text = "My Projects",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 10.dp,16.dp)
            )

            Text(text = "My Access Token : \n ${viewModel.token.value}")

            LazyColumn (
                modifier = Modifier.padding(10.dp)
            ){
                items(projects) { project ->
                    ProjectItem(project = project, onClick = { /* handle project item click */ })
                }
            }
        }
    }
}

@Composable
fun ProjectItem(project: Project, onClick: () -> Unit) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
            // .clickable { onClick() }
            ,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(text = project.name, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Text(text = project.dueDate, fontSize = 14.sp, color = Color.Gray)
            }
            IconButton(onClick = onClick) {
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "More Details"
                )
            }
        }

        Divider()
    }
}

data class Project(val name: String, val dueDate: String)


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomePreview(){
    PMTheme {
        //HomeScreen(rememberNavController())
    }
}