package com.example.kotlinmvvmbase.core.network_manual_parsing.client

import com.example.kotlinmvvmbase.core.config.AppConfig
import com.example.kotlinmvvmbase.core.network_manual_parsing.api.APIService
import com.example.kotlinmvvmbase.constant.Constant
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class Client {
    companion object {
        private val httpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        private val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(Interceptor { chain ->
                    val request = chain.request().newBuilder()
                            .addHeader("Accept", Constant.Network.ACCEPT_HEADER)
                            .addHeader("Content-Type", Constant.Network.CONTENT_TYPE_HEADER)
                            .addHeader("Accept-Language", AppConfig.USER_LANG)
                            .build()
                    chain.proceed(request)
                })
                .addInterceptor(httpLoggingInterceptor)
                .readTimeout(Constant.Network.READ_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(Constant.Network.CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .callTimeout(Constant.Network.CALL_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(Constant.Network.WRITE_TIMEOUT, TimeUnit.SECONDS)
                .build()

        val connect: APIService = Retrofit.Builder()
                .baseUrl(AppConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .client(okHttpClient)
                .build()
                .create(APIService::class.java)
    }
}