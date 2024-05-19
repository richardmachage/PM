package com.forsythe.pm.presentation.Screens.HomeScreen

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
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
import com.forsythe.pm.presentation.Screens.AccountScreen.AccountScreen
import com.forsythe.pm.presentation.Screens.destinations.AccountScreenDestination
import com.forsythe.pm.presentation.Screens.destinations.ArchivedProjectsScreenDestination
import com.forsythe.pm.presentation.Screens.destinations.CreateProjectScreenDestination
import com.forsythe.pm.presentation.others.FloatingAddButton
import com.forsythe.pm.presentation.ui.theme.PMTheme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator
) {
    val viewModel : HomeViewModel = hiltViewModel()

    val listOfProjects =  viewModel.listOfProjects.toList()

    LaunchedEffect(viewModel.refreshList.value) {
        viewModel.fetchProjects()
    }
    Scaffold(
        floatingActionButton = {
            FloatingAddButton(onClick = {
            /* handle add project click */
            //navController.navigate(route = "new_project_screen")
                navigator.navigate(CreateProjectScreenDestination)
            })
        },
        topBar = {
            TopAppBar(
                title = { Text(text = "Home") },
                actions = {
                    IconButton(onClick = {
                        navigator.navigate(AccountScreenDestination)
                    }) {
                        //got to account
                        Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "account")
                    }
                    IconButton(onClick = {
                        navigator.navigate(ArchivedProjectsScreenDestination)
                    }) {
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

            TextButton(onClick = { viewModel.refreshList.value + 1}) {
                Text(text = "Click to refresh list...")
            }
            
            if (listOfProjects.isNotEmpty()){
                LazyColumn (
                    modifier = Modifier.padding(10.dp)
                ){

                    items(listOfProjects) { project ->
                        ProjectItem(project = project, onClick = { /* handle project item click */ })
                    }
                }
            }else{
                Column(modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally) {

                    Text(text = "You do not have any projects currently\nClick button below to create")
                }
            }
            
        }
    }
}

@Composable
fun ProjectItem(project: com.forsythe.pm.models.Project, onClick: () -> Unit) {
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
                Text(text = project.Name, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Text(text = project.CreatedAt, fontSize = 14.sp, color = Color.Gray)
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