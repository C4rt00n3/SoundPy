package com.mupy.soundpy2.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class Api {
    object YouTube {
        private const val BASE_URL = "https://5011-128-201-181-45.ngrok-free.app/"
        private val client = OkHttpClient.Builder()
            .readTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .build()

        private val gson = GsonBuilder().setLenient().create()

        private val retrofit: Retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build()
        }

        val service: YouTubeService by lazy {
            retrofit.create(YouTubeService::class.java)
        }
    }
}