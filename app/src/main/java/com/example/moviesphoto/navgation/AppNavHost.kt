package com.example.moviesphoto.navgation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.moviesphoto.navgation.MyAppNavHost.Companion.HOME_SCREEN
import com.example.moviesphoto.screens.home.HomeScreen


@Preview
@Composable
fun MyAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = HOME_SCREEN
    ) {

        composable(route = HOME_SCREEN) {
            HomeScreen()
        }
    }
}


class MyAppNavHost {
    companion object {
        const val HOME_SCREEN: String = "home"
    }
}