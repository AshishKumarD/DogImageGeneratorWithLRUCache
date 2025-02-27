package com.ashish.dogimagegeneratorwithlrucache.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ashish.dogimagegeneratorwithlrucache.repository.DogRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.File

class MyDogsViewModel (private val repository: DogRepository) : ViewModel() {
    private val _cachedImages = MutableStateFlow<List<File>>(emptyList())
    val cachedImages = _cachedImages.asStateFlow()

    fun loadCachedImages() {
        viewModelScope.launch {
            _cachedImages.value = repository.getCachedDogImages()
        }
    }

    fun clearCache() {
        repository.clearCache()
        _cachedImages.value = emptyList() // Clear UI
    }
}