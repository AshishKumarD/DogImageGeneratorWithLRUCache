package com.ashish.dogimagegeneratorwithlrucache.routes

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ashish.dogimagegeneratorwithlrucache.ui.screens.GenerateDogsScreen
import com.ashish.dogimagegeneratorwithlrucache.ui.screens.GenerateDogsViewModel
import com.ashish.dogimagegeneratorwithlrucache.ui.screens.HomeScreen
import com.ashish.dogimagegeneratorwithlrucache.ui.screens.MyDogsScreen
import com.ashish.dogimagegeneratorwithlrucache.ui.screens.MyDogsViewModel

@Composable
fun AppNavHost(generateViewModel: GenerateDogsViewModel, myDogViewModel: MyDogsViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Home.route) {
        composable(Routes.Home.route) {
            HomeScreen(navController)
        }
        composable(Routes.GenerateDogs.route) {
            GenerateDogsScreen(generateViewModel)
        }
        composable(Routes.MyDogs.route) {
            MyDogsScreen(myDogViewModel)
        }
    }
}
