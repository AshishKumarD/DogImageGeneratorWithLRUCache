package com.ashish.dogimagegeneratorwithlrucache.repository

class DogRepository(private val apiService: DogApiService) {

    suspend fun fetchRandomDog(): String? {
        return try {
            val response = apiService.getRandomDog()
            if (response.isSuccessful) {
                response.body()?.imageUrl
            } else null
        } catch (e: Exception) {
            null
        }
    }

}
