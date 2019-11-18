package br.com.mobile_test.api

import br.com.mobile_test.model.MovieResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchAPI {

    @GET("search/movie/")
    fun getMovies(
        @Query("query") query : String,
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Single<MovieResponse>
}