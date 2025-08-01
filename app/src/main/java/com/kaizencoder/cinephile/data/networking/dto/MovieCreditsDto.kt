package com.kaizencoder.cinephile.data.networking.dto

import com.kaizencoder.cinephile.domain.model.CastMember
import com.kaizencoder.cinephile.domain.model.Credits
import com.kaizencoder.cinephile.domain.model.CrewMember

data class MovieCreditsDto(
    val cast: List<Cast>,
    val crew: List<Crew>,
    val id: Int
)

data class Cast(
    val adult: Boolean,
    val cast_id: Int,
    val character: String?,
    val credit_id: String,
    val gender: Int,
    val id: Int,
    val known_for_department: String,
    val name: String,
    val order: Int,
    val original_name: String,
    val popularity: Double,
    val profile_path: String?
)

data class Crew(
    val adult: Boolean,
    val credit_id: String,
    val department: String,
    val gender: Int,
    val id: Int,
    val job: String,
    val known_for_department: String,
    val name: String,
    val original_name: String,
    val popularity: Double,
    val profile_path: String?
)

fun Cast.toCastMember(): CastMember = CastMember(
    id = id,
    name = name,
    character = character,
    order = order,
    profilePath = profile_path
)

fun Crew.toCrewMember(): CrewMember = CrewMember(
    id = id,
    name = name,
    department = known_for_department,
    job = job,
    profilePath = profile_path
)

fun MovieCreditsDto.toCredits(): Credits = Credits(
    id = id,
    castMembers = cast.map { it.toCastMember() },
    directors = crew.filter { it.job == "Director" }.map { it.toCrewMember() },
    writers = crew.filter { it.job == "Screenplay" }.map { it.toCrewMember() }
)
