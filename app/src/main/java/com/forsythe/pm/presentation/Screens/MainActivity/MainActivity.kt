package com.forsythe.pm.presentation.Screens.MainActivity

import android.os.Bundle
import android.provider.DocumentsContract.Root
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.forsythe.pm.data.sharedPreferences.ACCESS_TOKEN_KEY
import com.forsythe.pm.data.sharedPreferences.PreferencesRepo
import com.forsythe.pm.presentation.Screens.LogInScreen.LoginScreen
import com.forsythe.pm.presentation.navigation.SetNavGraph
import com.forsythe.pm.presentation.ui.theme.PMTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val maninViewModel: MainViewModel by viewModels()
    lateinit var navHostController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val preferencesRepo =  PreferencesRepo( context = applicationContext)

        setContent {

            PMTheme {
                navHostController = rememberNavController()
                SetNavGraph(navHostController = navHostController)

            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PMTheme {
       // Greeting("Android")
    }
}