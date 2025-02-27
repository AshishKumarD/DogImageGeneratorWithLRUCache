package com.ashish.dogimagegeneratorwithlrucache.routes

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ashish.dogimagegeneratorwithlrucache.ui.screens.GenerateDogsScreen
import com.ashish.dogimagegeneratorwithlrucache.ui.screens.GenerateDogsViewModel
import com.ashish.dogimagegeneratorwithlrucache.ui.screens.HomeScreen
import com.ashish.dogimagegeneratorwithlrucache.ui.screens.MyDogsScreen

@Composable
fun AppNavHost(viewModel: GenerateDogsViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Home.route) {
        composable(Routes.Home.route) {
            HomeScreen(navController)
        }
        composable(Routes.GenerateDogs.route) {
            GenerateDogsScreen(viewModel)
        }
        composable(Routes.MyDogs.route) {
            MyDogsScreen(navController)
        }
    }
}
