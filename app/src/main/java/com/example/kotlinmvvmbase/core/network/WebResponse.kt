package com.example.kotlinmvvmbase.core.network

import com.google.gson.annotations.SerializedName


class WebResponse<T> {
    @SerializedName("data")
    val data: T? = null
}