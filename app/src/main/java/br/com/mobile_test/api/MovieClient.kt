package br.com.mobile_test.api


import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory



private const val API_URL = "https://api.themoviedb.org/3/"

class MovieClient<T> {

    fun getClient(c: Class<T>): T {

        val retrofit = Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        return retrofit.create(c)
    }


}

fun getMoviesAPI(): MovieAPI {
    return MovieClient<MovieAPI>().getClient(MovieAPI::class.java)
}

fun getGenreAPI(): GenreAPI{
    return MovieClient<GenreAPI>().getClient(GenreAPI::class.java)
}

fun getSearchAPI() : SearchAPI{
    return MovieClient<SearchAPI>().getClient(SearchAPI::class.java)
}