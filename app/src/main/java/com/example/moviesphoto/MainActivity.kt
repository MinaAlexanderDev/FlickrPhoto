package com.example.moviesphoto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.moviesphoto.screens.home.HomeScreen
import com.example.moviesphoto.ui.theme.IMOVIESTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController: NavHostController = rememberNavController()
            IMOVIESTheme {
                HomeScreen()
//                Surface(
//                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
//                ) {
//                    MyAppNavHost(
//                        navController = navController, modifier = Modifier
//                    )
//
//                }
            }
        }
    }
}


