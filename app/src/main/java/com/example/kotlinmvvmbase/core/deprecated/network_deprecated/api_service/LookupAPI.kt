package com.example.kotlinmvvmbase.core.deprecated.network_deprecated.api_service

import com.example.kotlinmvvmbase.core.network.model.WebResponse
import com.example.kotlinmvvmbase.core.deprecated.network_deprecated.model.response.datamodel.parts.PartsList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface LookupAPI {

    @GET("/api/v1/application/part-catalogue/sub-categories/parts")
    fun getParts(@Query("sub_category_id") sub_category_id: String,
                 @Query("chassis_id") chassis_id: String): Call<WebResponse<PartsList>>

    @GET("/api/v1/application/part-catalogue-parts")
    fun getUnProcessableEntityError(
            @Query("part_catalogue_id") part_catalogue_id: String,
            @Query("page") page: String): Call<WebResponse<PartsList>>
}