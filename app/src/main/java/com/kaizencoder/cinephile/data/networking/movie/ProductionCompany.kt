package com.kaizencoder.cinephile.data.networking.movie


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductionCompany(
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "logo_path")
    val logoPath: String?,
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "origin_country")
    val originCountry: String
)
