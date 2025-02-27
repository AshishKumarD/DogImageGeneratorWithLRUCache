package com.ashish.dogimagegeneratorwithlrucache.ui.screens

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.ashish.dogimagegeneratorwithlrucache.ui.components.OutlinedBlueButton

@Composable
fun MyDogsScreen(viewModel: MyDogsViewModel) {
    val cachedImages by viewModel.cachedImages.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadCachedImages()
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
        ) {

        LazyRow(modifier = Modifier) {
            items(cachedImages) { file ->
                val fileUri = Uri.fromFile(file)
                Image(
                    painter = rememberAsyncImagePainter(fileUri),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(300.dp)
                        .padding(horizontal = 10.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedBlueButton(
            onClick = { viewModel.clearCache() },
            text = "Clear Dogs"
        )

    }
}