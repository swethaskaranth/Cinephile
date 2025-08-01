package com.kaizencoder.cinephile.domain.model

data class CastMember( val id: Int,
                       val name: String,
                       val character: String?,
                       val order: Int,
                       val profilePath: String?)
