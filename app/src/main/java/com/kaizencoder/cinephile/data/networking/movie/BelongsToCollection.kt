package com.kaizencoder.cinephile.data.networking.movie


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BelongsToCollection(
    @field:Json(name = "backdrop_path")
    val backdropPath: String,
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "poster_path")
    val posterPath: String
)
