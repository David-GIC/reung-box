package com.daviddev.reungbox.data.remote

import com.daviddev.reungbox.domain.models.Movie
import com.daviddev.reungbox.domain.models.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("search")
    suspend fun getAllMovies(
        @Query("type") type: String = "movie",
        @Query("genre") genre: String?,
        @Query("rows") rows: Int? = 10,
        @Query("sortOrder") sortOrder: String = "DESC",
        @Query("sortField") sortField: String = "startYear"
    ): MovieResponse

    @GET("{movieId}")
    suspend fun getMovieById(@Path("movieId") movieId: String): Movie

    @GET("most-popular-movies")
    suspend fun getPopularMovies(): List<Movie>

    @GET("top250-movies")
    suspend fun getTop250Movies(): List<Movie>

    @GET("top-rated-english-movies")
    suspend fun getTopRatingMovies(): List<Movie>
}
