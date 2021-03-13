package com.example.kotlinmvvmbase.core.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroInstance {
    companion object {
        private val BASE_URL = "http://18.193.126.108:8080"

        private val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        private val okHttp = OkHttpClient.Builder().addInterceptor(logger)

        private val instance: Retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .client(okHttp.build())
                .build()

        fun <VM> getAPI(modelClass: Class<VM>): VM {
            return instance.create(modelClass)
        }
    }
}
