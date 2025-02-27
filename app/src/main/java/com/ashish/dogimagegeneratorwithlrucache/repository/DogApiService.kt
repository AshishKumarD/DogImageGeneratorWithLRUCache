package com.ashish.dogimagegeneratorwithlrucache.repository

import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.http.GET

interface DogApiService {
    @GET("breeds/image/random")
    suspend fun getRandomDog(): Response<DogResponse>
}

data class DogResponse(
    @SerializedName("message") val imageUrl: String,
    @SerializedName("status") val status: String
)
