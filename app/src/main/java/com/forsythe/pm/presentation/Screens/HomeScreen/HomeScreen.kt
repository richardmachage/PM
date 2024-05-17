package com.forsythe.pm.presentation.Screens.HomeScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.forsythe.pm.presentation.Screens.SignUpScreen
import com.forsythe.pm.presentation.others.FloatingAddButton
import com.forsythe.pm.presentation.ui.theme.PMTheme

@Composable
fun HomeScreen() {
    val projects = listOf(
        Project("Product Launch 2023", "Due 2/28/23"),
        Project("Data Center Expansion", "Due 12/30/22"),
        Project("Q4 Sales Campaign", "Due 11/10/22"),
        Project("Board of Directors Meeting", "Due 1/20/23")
    )

    Scaffold(
        floatingActionButton = {
            FloatingAddButton(onClick = { /* handle add project click */ })
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
                modifier = Modifier.padding(start = 1.dp,16.dp)
            )


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
        HomeScreen()
    }
}