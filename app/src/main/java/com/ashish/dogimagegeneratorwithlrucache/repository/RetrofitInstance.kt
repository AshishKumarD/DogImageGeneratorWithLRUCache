package com.ashish.dogimagegeneratorwithlrucache.repository


import com.ashish.dogimagegeneratorwithlrucache.utils.Constants.DOG_CEO_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val apiService: DogApiService by lazy {
        Retrofit.Builder()
            .baseUrl(DOG_CEO_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DogApiService::class.java)
    }
}
