package com.kaizencoder.cinephile.data.mapper

import com.kaizencoder.cinephile.data.networking.credits.MovieCreditsDto
import com.kaizencoder.cinephile.data.networking.credits.toCastMember
import com.kaizencoder.cinephile.data.networking.credits.toCrewMember
import com.kaizencoder.cinephile.domain.model.Credits

fun MovieCreditsDto.toCredits(): Credits = Credits(
    id = id,
    castMembers = cast.map { it.toCastMember() },
    directors = crew.filter { it.job == "Director" }.map { it.toCrewMember() },
    writers = crew.filter { it.job == "Screenplay" }.map { it.toCrewMember() }
)
