package com.example.mininetflixx.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {
    @GET("api/movies")
    suspend fun getMovies(): List<Video>
}

object RetrofitClient {
    // ¡OJO! 10.0.2.2 es para conectarse a la PC desde el Emulador de Android.
    private const val BASE_URL = "http://192.168.100.5:5000/"

    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}