package com.daviddev.reungbox.ui.screen.home

import com.daviddev.reungbox.domain.models.Movie

data class HomeScreenUiState(
    val banner: List<Movie> = emptyList(),
    val top250Movies: List<Movie> = emptyList(),
    val topRateMovie: List<Movie> = emptyList(),
    val popular: List<Movie> = emptyList(),
    val movies: List<Movie> = emptyList(),
    val isLoading: Boolean = true,
    val error: String? = null
)
