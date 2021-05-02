package com.example.kotlinmvvmbase.core.deprecated.network_deprecated

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroInstance {
    companion object {
        private val BASE_URL = "http://18.193.126.108:8080"

        private val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        private val myClient = OkHttpClient.Builder().addInterceptor(Interceptor { chain ->
            val request = chain.request().newBuilder()
                    .addHeader("Accept", "application/json")
                    //ToDO: Change language header when completing Caching Layer
                    .addHeader("Accept-Language", "en")
                    .build()
            chain.proceed(request)
        }).addInterceptor(logger).build()

        private val instance: Retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .client(myClient)
                .build()

        fun <VM> getAPI(modelClass: Class<VM>): VM {
            return instance.create(modelClass)
        }
    }
}
