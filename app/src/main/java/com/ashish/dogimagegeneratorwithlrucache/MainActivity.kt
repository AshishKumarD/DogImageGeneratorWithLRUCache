package com.ashish.dogimagegeneratorwithlrucache

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ashish.dogimagegeneratorwithlrucache.repository.DogApiService
import com.ashish.dogimagegeneratorwithlrucache.repository.DogRepository
import com.ashish.dogimagegeneratorwithlrucache.repository.RetrofitInstance
import com.ashish.dogimagegeneratorwithlrucache.routes.AppNavHost
import com.ashish.dogimagegeneratorwithlrucache.ui.screens.GenerateDogsViewModel
import com.ashish.dogimagegeneratorwithlrucache.ui.screens.MyDogsViewModel
import com.ashish.dogimagegeneratorwithlrucache.utils.Constants.DOG_CEO_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val repository = DogRepository(RetrofitInstance.apiService, this.applicationContext)

            AppNavHost(GenerateDogsViewModel(repository), MyDogsViewModel(repository) ) // Pass ViewModel to navigation
        }
    }
}
