package com.daviddev.reungbox.ui.screen.movie_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daviddev.reungbox.data.client.ApiInstance
import com.daviddev.reungbox.ui.screen.home.HomeScreenUiState
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieDetailViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(MovieDetailUiState())
    val uiState: StateFlow<MovieDetailUiState> = _uiState

    val movieApi = ApiInstance.movieApi

    fun getMovieById(movieId: String) {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(isLoading = true)
                val getMovie = async { movieApi.getMovieById(movieId) }.await()
                _uiState.value = MovieDetailUiState(
                    movie = getMovie,
                    isLoading = false,
                    error = null
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message ?: "Unknown error"
                )
            }
        }
    }
}