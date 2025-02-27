package com.ashish.dogimagegeneratorwithlrucache.ui.screens

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import com.ashish.dogimagegeneratorwithlrucache.ui.components.DogImage
import com.ashish.dogimagegeneratorwithlrucache.ui.components.OutlinedBlueButton
import java.net.URL
import kotlin.concurrent.thread

@Composable
fun GenerateDogsScreen(viewModel: GenerateDogsViewModel) {
    val dogImageUrl by viewModel.dogImageUrl.collectAsState()

    Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
    ) {
        dogImageUrl?.let { imageUrl -> DogImage(url = imageUrl) }
        OutlinedBlueButton(
                text = "Generate!",
                onClick = { viewModel.generateDog() },
        )
    }
}



