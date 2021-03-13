package com.example.kotlinmvvmbase.core.network.model.response.datamodel.parts

data class PartCatalogue(
    val cover: CoverX?,
    val distance: String?,
    val id: String?,
    val latitude: Any?,
    val logo: Logo?,
    val longitude: Any?,
    val name: String?,
    val phone: List<String>?,
    val provider_flag: Int?,
    val rate: Double?,
    val reviews_count: Int?
)