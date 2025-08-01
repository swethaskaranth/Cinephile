package com.kaizencoder.cinephile.domain.model

data class Credits(
    val id: Int,
    val castMembers: List<CastMember>,
    val directors: List<CrewMember>,
    val writers: List<CrewMember>,
)
