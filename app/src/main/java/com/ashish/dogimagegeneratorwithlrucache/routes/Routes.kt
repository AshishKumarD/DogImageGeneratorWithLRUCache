package com.ashish.dogimagegeneratorwithlrucache.routes
sealed class Routes(val route: String) {
    object Home : Routes("home")
    object GenerateDogs : Routes("generate_dogs")
    object MyDogs : Routes("my_dogs")
}