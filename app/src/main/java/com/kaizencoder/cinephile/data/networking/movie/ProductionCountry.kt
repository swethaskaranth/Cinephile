package com.kaizencoder.cinephile.data.networking.movie


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductionCountry(
    @field:Json(name = "iso_3166_1")
    val iso31661: String,
    @field:Json(name = "name")
    val name: String
)
