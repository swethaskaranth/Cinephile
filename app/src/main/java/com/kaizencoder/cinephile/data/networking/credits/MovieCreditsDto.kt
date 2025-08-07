package com.kaizencoder.cinephile.data.networking.credits

import com.kaizencoder.cinephile.domain.model.CastMember
import com.kaizencoder.cinephile.domain.model.CrewMember
import com.squareup.moshi.Json

data class MovieCreditsDto(
    val cast: List<Cast>,
    val crew: List<Crew>,
    val id: Int
)

data class Cast(
    val adult: Boolean,
    @field:Json(name = "cast_id")
    val castId: Int,
    val character: String?,
    @field:Json(name = "credit_id")
    val creditId: String,
    val gender: Int,
    val id: Int,
    @field:Json(name = "known_for_department")
    val knownForDepartment: String,
    val name: String,
    val order: Int,
    @field:Json(name = "original_name")
    val originalName: String,
    val popularity: Double,
    @field:Json(name = "profile_path")
    val profilePath: String?
)

data class Crew(
    val adult: Boolean,
    @field:Json(name = "credit_id")
    val creditId: String,
    val department: String,
    val gender: Int,
    val id: Int,
    val job: String,
    @field:Json(name = "known_for_department")
    val knownForDepartment: String,
    val name: String,
    @field:Json(name = "original_name")
    val originalName: String,
    val popularity: Double,
    @field:Json(name = "profile_path")
    val profilePath: String?
)

fun Cast.toCastMember(): CastMember = CastMember(
    id = id,
    name = name,
    character = character,
    order = order,
    profilePath = profilePath
)

fun Crew.toCrewMember(): CrewMember = CrewMember(
    id = id,
    name = name,
    department = knownForDepartment,
    job = job,
    profilePath = profilePath
)
