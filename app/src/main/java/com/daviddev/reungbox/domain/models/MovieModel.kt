package com.daviddev.reungbox.domain.models
import kotlinx.serialization.Serializable
@Serializable
data class MovieResponse(
    val rows: Int,
    val numFound: Int,
    val results: List<Movie>,
    val nextCursorMark: String?
)
@Serializable
data class Movie (
    val id: String,
    val url: String,
    val primaryTitle: String,
    val originalTitle: String,
    val type: String,
    val description: String,
    val primaryImage: String?,
    val contentRating: String?,
    val startYear: Long?,
    val releaseDate: String?,
    val interests: List<String>,
    val countriesOfOrigin: List<String>,
    val spokenLanguages: List<String>,
    val filmingLocations: List<String>,
    val grossWorldwide: Long?,
    val genres: List<String>,
    val isAdult: Boolean?,
    val runtimeMinutes: Long?,
    val averageRating: Double?,
)
