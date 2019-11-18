package br.com.mobile_test.api


import br.com.mobile_test.model.GenreResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface GenreAPI {

    @GET("genre/movie/list")
    fun getGenres(@Query("api_key") apiKey: String, @Query("language") language: String) : Single<GenreResponse>
}