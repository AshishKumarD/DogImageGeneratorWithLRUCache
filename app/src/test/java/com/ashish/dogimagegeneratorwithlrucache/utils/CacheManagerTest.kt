package com.ashish.dogimagegeneratorwithlrucache.utils


import android.content.Context
import android.graphics.Bitmap
import androidx.test.core.app.ApplicationProvider
import junit.framework.TestCase.assertNotNull
import org.junit.After
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import java.io.File

import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class CacheManagerTest {

    private lateinit var context: Context
    private lateinit var testCacheDir: File
    private lateinit var testBitmap: Bitmap
    private val testKey = "test_image"

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()

        // Set up a temporary cache directory
        testCacheDir = File(context.cacheDir, "test_cache").apply { mkdirs() }

        // Create a small sample bitmap
        testBitmap = Bitmap.createBitmap(10, 10, Bitmap.Config.ARGB_8888)
    }

    @After
    fun tearDown() {
        testCacheDir.deleteRecursively()
    }

    @Test
    fun `test save and load image from memory cache`() {
        CacheManager.saveImage(context, testKey, testBitmap)

        // Load from memory cache
        val loadedBitmap = CacheManager.loadImage(context, testKey)

        assertNotNull("Bitmap should be loaded from memory cache", loadedBitmap)
    }

    @Test
    fun `test save and load image from disk cache`() {
        CacheManager.saveImage(context, testKey, testBitmap)

        // Load from disk cache
        val loadedBitmap = CacheManager.loadImage(context, testKey)

        assertNotNull( "Bitmap should be loaded from disk cache", loadedBitmap)
    }

    @Test
    fun `test load image fails if not cached`() {
        val loadedBitmap = CacheManager.loadImage(context, "non_existent_key")
        assertNull("Bitmap should not be found", loadedBitmap)
    }
}