package com.ashish.dogimagegeneratorwithlrucache.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ashish.dogimagegeneratorwithlrucache.repository.DogRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class GenerateDogsViewModel(private val repository: DogRepository) : ViewModel() {
    private val _dogImageUrl = MutableStateFlow<String?>(null)
    val dogImageUrl = _dogImageUrl.asStateFlow()

    fun generateDog() {
        viewModelScope.launch {
            val imageUrl = repository.fetchRandomDog()
            _dogImageUrl.value = imageUrl // Update UI
        }
    }
}
