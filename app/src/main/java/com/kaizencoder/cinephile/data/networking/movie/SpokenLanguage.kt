package com.kaizencoder.cinephile.data.networking.movie


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SpokenLanguage(
    @field:Json(name = "english_name")
    val englishName: String,
    @field:Json(name = "iso_639_1")
    val iso6391: String,
    @field:Json(name = "name")
    val name: String
)
