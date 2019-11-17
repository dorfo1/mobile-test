package br.com.mobile_test.api

import br.com.mobile_test.model.AtorResponse
import br.com.mobile_test.model.Movie
import br.com.mobile_test.model.MovieDetail
import br.com.mobile_test.model.MovieResponse
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MovieAPI {


    @GET("movie/popular/")
    fun getMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Single<MovieResponse>


    @GET("movie/{movie_id}/credits")
    fun getMovieCast(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Single<AtorResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetails(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Single<MovieDetail>
}