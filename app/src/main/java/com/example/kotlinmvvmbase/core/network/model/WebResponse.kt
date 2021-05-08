package com.example.kotlinmvvmbase.core.network.model

import com.google.gson.annotations.SerializedName


class WebResponse<T> {
    @SerializedName("data")
    val data: T? = null
    val message: String? = null
}