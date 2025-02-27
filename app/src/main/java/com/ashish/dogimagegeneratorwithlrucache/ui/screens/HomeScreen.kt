package com.ashish.dogimagegeneratorwithlrucache.ui.screens


import android.content.res.Resources.Theme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ashish.dogimagegeneratorwithlrucache.routes.Routes
import com.ashish.dogimagegeneratorwithlrucache.ui.components.OutlinedBlueButton
import com.ashish.dogimagegeneratorwithlrucache.ui.theme.DogImageGeneratorWithLRUCacheTheme


@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Random Dog Generator!",
            style = MaterialTheme.typography.headlineSmall,
        )

        Column(

            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedBlueButton(
                onClick = { navController.navigate(Routes.GenerateDogs.route) },
                text = "Generate Dogs!"
            )
            OutlinedBlueButton(
                onClick = { navController.navigate(Routes.MyDogs.route) },
                text = "My Recently Generated Dogs!"
            )

        }
    }

}

@Preview(showBackground = true)
@Composable
fun TestText(){
    DogImageGeneratorWithLRUCacheTheme {
        OutlinedBlueButton(
            onClick = {  },
            text = "My Recently Generated Dogs!",
            modifier = Modifier.fillMaxSize()
        )
    }
}
