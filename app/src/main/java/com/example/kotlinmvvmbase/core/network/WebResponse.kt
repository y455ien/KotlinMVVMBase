package com.example.kotlinmvvmbase.core.network

import com.example.kotlinmvvmbase.core.network.model.response.datamodel.error.ErrorList
import com.google.gson.annotations.SerializedName


class WebResponse<T> {
    @SerializedName("errors")
    val error: ErrorList? = null

    @SerializedName("data")
    val data: T? = null
}