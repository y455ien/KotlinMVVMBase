package com.example.kotlinmvvmbase.core.deprecated.network_deprecated.model.response.datamodel.parts

data class Data(
        val cover: Cover?,
        val created_at: String?,
        val discount_percentage: Int?,
        val discount_price: Int?,
        val id: String?,
        val is_featured: Boolean?,
        val name: String?,
        val part_catalogue: PartCatalogue?,
        val part_number: String?,
        val part_status: Int?,
        val price: String?,
        val start_date: String?,
        val start_date_formated: String?
)