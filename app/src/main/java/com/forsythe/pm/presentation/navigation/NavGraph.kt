package com.forsythe.pm.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.forsythe.pm.presentation.Screens.ArchivedScreeen.ArchivedProjectsScreen
import com.forsythe.pm.presentation.Screens.HomeScreen.HomeScreen
import com.forsythe.pm.presentation.Screens.LogInScreen.LoginScreen
import com.forsythe.pm.presentation.Screens.NewProjectScreen.CreateProjectScreen
import com.forsythe.pm.presentation.Screens.RegisterScreen.SignUpScreen

@Composable
fun SetNavGraph(
    navHostController : NavHostController
){
   NavHost(navController = navHostController, startDestination ="login_screen" ) {
       composable(route = "login_screen"){ LoginScreen(navHostController)}
       composable(route = "signup_screen"){ SignUpScreen(navHostController) }
       composable(route = "home_screen"){ HomeScreen(navHostController) }
       composable(route = "new_project_screen"){ CreateProjectScreen(navHostController) }
       composable(route = "archived_screen"){ ArchivedProjectsScreen(navHostController) }
   }
}