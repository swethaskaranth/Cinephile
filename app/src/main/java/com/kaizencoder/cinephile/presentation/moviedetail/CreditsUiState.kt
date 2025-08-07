package com.kaizencoder.cinephile.presentation.moviedetail

import com.kaizencoder.cinephile.domain.model.CastMember
import com.kaizencoder.cinephile.domain.model.CrewMember

data class CreditsUiState(val cast: List<CastMember> = emptyList(),
                          val directors: List<CrewMember> = emptyList(),
                          val writers: List<CrewMember> = emptyList(),
                          val isLoading: Boolean = false,
                          val error: String? = null)
