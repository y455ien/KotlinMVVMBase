package com.example.kotlinmvvmbase.core.network.model.response.datamodel.parts

data class Cover(
    val custom_properties: List<Any>?,
    val id: Int?,
    val mime_type: String?,
    val path: String?,
    val path_thumbnail: String?
)