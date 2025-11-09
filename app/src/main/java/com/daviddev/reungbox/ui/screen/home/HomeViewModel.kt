package com.daviddev.reungbox.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daviddev.reungbox.data.client.ApiInstance
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {
    val genres = listOf(
            "All",
            "Drama",
            "Documentary",
            "Comedy",
            "Action",
            "Romance",
            "Thriller",
            "Crime",
            "Horror",
            "Music",
            "Adventure",
            "Family",
            "Animation",
            "Reality-TV",
            "Mystery",
            "History",
            "Talk-Show",
            "Biography",
            "Sport",
            "Fantasy",
            "Sci-Fi",
            "Musical",
            "News",
            "War",
            "Adult",
            "Game-Show",
            "Western",
            "Short",
            "Film-Noir"
    )

    val movieApi = ApiInstance.movieApi

    private val _uiState = MutableStateFlow(HomeScreenUiState())
    private val _genresState = MutableStateFlow("All");
    val uiState: StateFlow<HomeScreenUiState> = _uiState
    val genresState: StateFlow<String?> = _genresState

    init {
        fetchAllData()
    }

    fun fetchAllData() {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(isLoading = true)

                val moviesDeferred = async { movieApi.getAllMovies(genre = if(genresState.value == "All") null else genresState.value) }
                val top250Deferred = async { movieApi.getTop250Movies() }
                val popularDeferred = async { movieApi.getPopularMovies() }
                val topRateDeferred = async { movieApi.getTopRatingMovies() }

                // Await all at once (after starting all async calls)
                val movies = moviesDeferred.await()
                val top250Movies = top250Deferred.await()
                val popular = popularDeferred.await()
                val topRateMovie = topRateDeferred.await()

                _uiState.value = HomeScreenUiState(
                    banner = popular.take(5).map { it },
                    top250Movies = top250Movies,
                    topRateMovie = topRateMovie,
                    popular = popular,
                    movies = movies.results,
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

    fun onUpdateGenres(value: String){
        _genresState.value = value;
    }

}