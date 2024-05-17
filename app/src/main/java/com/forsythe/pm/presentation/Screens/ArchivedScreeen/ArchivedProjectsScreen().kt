package com.forsythe.pm.presentation.Screens.ArchivedScreeen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.forsythe.pm.presentation.ui.theme.PMTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArchivedProjectsScreen() {
    val archivedProjects = listOf(
        ArchivedProject("Design Sprint: Q3 OKR Planning", 5, 2),
        ArchivedProject("Q1 2022 Roadmap", 4, 1),
        ArchivedProject("Design Sprint: Q4 OKR Planning", 6, 3)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Archived Projects",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /* handle back click */ }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp)
            ) {
                LazyColumn {
                    items(archivedProjects) { project ->
                        ArchivedProjectItem(
                            project = project,
                            onClick = { /* handle project item click */ },
                            onDelete = { /* handle delete project */ },
                            onUnarchive = { /* handle unarchive project */ }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Delete all archived projects",
                    color = Color.Red,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .clickable { /* handle delete all archived projects click */ }
                        .padding(16.dp)
                )
            }
        }
    )
}
@Composable
fun ArchivedProjectItem(
    project: ArchivedProject,
    onClick: () -> Unit,
    onDelete: () -> Unit,
    onUnarchive: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Filled.KeyboardArrowUp,
            contentDescription = "Unarchive",
            modifier = Modifier
                .padding(16.dp)
                .clickable { onUnarchive() }
        )
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(text = project.name, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Text(text = "${project.tasks} tasks, ${project.sections} sections", fontSize = 14.sp, color = Color.Gray)
        }
        IconButton(onClick = onDelete) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete"
            )
        }
    }
}
data class ArchivedProject(val name: String, val tasks: Int, val sections: Int)

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ArchivedProjectPreview(){
    PMTheme {
        ArchivedProjectsScreen()
    }
}