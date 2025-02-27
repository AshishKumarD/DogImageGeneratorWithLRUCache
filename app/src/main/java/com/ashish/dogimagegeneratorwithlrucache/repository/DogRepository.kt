package com.ashish.dogimagegeneratorwithlrucache.repository

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.ashish.dogimagegeneratorwithlrucache.utils.CacheManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.net.URL

class DogRepository(private val apiService: DogApiService, private val context: Context) {

    suspend fun fetchRandomDog(): String? {
        return try {
            val response = apiService.getRandomDog()
            if (response.isSuccessful) {
                val imageUrl = response.body()?.imageUrl ?: return null
                val cacheKey = imageUrl.hashCode().toString()

                // Load from cache first
                val cachedImage = CacheManager.loadImage(context, cacheKey)
                if (cachedImage == null) {
                    val bitmap = downloadImage(imageUrl)
                    bitmap?.let { CacheManager.saveImage(context, cacheKey, it) }
                }

                imageUrl
            } else null
        } catch (e: Exception) {
            null
        }
    }

    fun getCachedDogImages(): List<File> {
        val cacheDir = File(context.cacheDir, "image_cache")
        return cacheDir.listFiles()?.toList() ?: emptyList()
    }

    fun clearCache() {
        val cacheDir = File(context.cacheDir, "image_cache")
        cacheDir.listFiles()?.forEach { it.delete() }
    }

    private suspend fun downloadImage(url: String): Bitmap? {
        return withContext(Dispatchers.IO) {
            try {
                val inputStream = URL(url).openStream()
                BitmapFactory.decodeStream(inputStream)
            } catch (e: Exception) {
                null
            }
        }
    }
}

