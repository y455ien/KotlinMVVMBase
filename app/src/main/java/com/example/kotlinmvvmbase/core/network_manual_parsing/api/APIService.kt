package com.example.kotlinmvvmbase.core.network_manual_parsing.api

import com.example.kotlinmvvmbase.core.network.WebResponse
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.*

interface APIService {
    @GET
    fun GET(@Url url: String,
            @QueryMap params: HashMap<String, Any>? = hashMapOf()): Call<JsonElement>

    @POST
    fun POST(@Url url: String,
             @Body params: HashMap<String, Any>? = hashMapOf()): Call<JsonElement>

    @POST
    suspend fun coroutinePost(@Url url: String,
             @Body params: HashMap<String, Any>? = hashMapOf()): WebResponse<String>

    @GET
    suspend fun coroutineGet(@Url url: String,
            @QueryMap params: HashMap<String, Any>? = hashMapOf()): WebResponse<String>

}