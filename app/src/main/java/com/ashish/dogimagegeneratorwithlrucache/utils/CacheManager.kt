package com.ashish.dogimagegeneratorwithlrucache.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.LruCache
import java.io.File
import java.io.FileOutputStream

object CacheManager {
    private const val CACHE_DIR = "image_cache"
    private const val MAX_DISK_CACHE_SIZE = 20 // Limit to 20 images

    // LRU Cache
    // Limiting Image size to 10mb
    private val memoryCache: LruCache<String, Bitmap> =
            object : LruCache<String, Bitmap>(10 * 1024 * 1024) {
                override fun sizeOf(key: String, value: Bitmap): Int {
                    return value.byteCount
                }
            }

    /** Save image to cache (LRU + Persistent Storage) */
    fun saveImage(context: Context, key: String, bitmap: Bitmap) {
        memoryCache.put(key, bitmap)
        saveToInternalStorage(context, key, bitmap)
        enforceDiskCacheLimit(context)
    }

    /** Load image from cache (LRU → Persistent Storage) */
    fun loadImage(context: Context, key: String): Bitmap? {
        return memoryCache.get(key) ?: loadFromInternalStorage(context, key)
    }

    /** Save bitmap to internal storage */
    private fun saveToInternalStorage(context: Context, key: String, bitmap: Bitmap) {
        val cacheDir = File(context.cacheDir, CACHE_DIR)
        if (!cacheDir.exists()) cacheDir.mkdirs()

        val file = File(cacheDir, "$key.png")
        FileOutputStream(file).use { bitmap.compress(Bitmap.CompressFormat.PNG, 100, it) }
    }

    /** Load bitmap from internal storage */
    private fun loadFromInternalStorage(context: Context, key: String): Bitmap? {
        val file = File(context.cacheDir, "$CACHE_DIR/$key.png")
        return if (file.exists()) BitmapFactory.decodeFile(file.absolutePath) else null
    }

    /** Enforce disk cache limit (keep only the latest 20 images) */
    private fun enforceDiskCacheLimit(context: Context) {
        val cacheDir = File(context.cacheDir, CACHE_DIR)
        val files = cacheDir.listFiles()?.sortedBy { it.lastModified() }?.toMutableList() ?: return

        if (files.size > MAX_DISK_CACHE_SIZE) {
            val extraFiles = files.size - MAX_DISK_CACHE_SIZE
            files.take(extraFiles).forEach { it.delete() } // Delete oldest files
        }
    }
}
