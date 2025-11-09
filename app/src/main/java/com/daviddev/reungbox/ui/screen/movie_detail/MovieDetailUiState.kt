package com.daviddev.reungbox.ui.screen.movie_detail

import com.daviddev.reungbox.domain.models.Movie

data class MovieDetailUiState(
    val movie: Movie? = null,
    val isLoading: Boolean = true,
    val error: String? = null
)