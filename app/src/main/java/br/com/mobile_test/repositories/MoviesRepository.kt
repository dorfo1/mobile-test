package br.com.mobile_test.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import br.com.mobile_test.api.getGenreAPI
import br.com.mobile_test.api.getMoviesAPI
import br.com.mobile_test.model.*
import br.com.mobile_test.utils.MovieResource
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

object MoviesRepository {


    private val _movieDetails = MutableLiveData<MovieResource<MovieDetail>>()
    val movieDetails get() = _movieDetails

//    fun fetchMovies(apiKey: String, language: String) {
//        _movies.postValue(MovieResource.Loading(null))
//        Single.zip(
//            getMoviesAPI().getMovies(apiKey, language,1),
//            getGenreAPI().getGenres(apiKey, language),
//            BiFunction { movieResponse: MovieResponse, genreResponse: GenreResponse ->
//                filtrarGeneroFilme(movieResponse, genreResponse.genres)
//            })
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(object : DisposableSingleObserver<MovieResponse>(){
//                override fun onSuccess(t: MovieResponse) {
//                    _movies.postValue(MovieResource.Success(t))
//                }
//
//                override fun onError(e: Throwable) {
//                    _movies.postValue(MovieResource.Error(null, "Falha ao realizar a requisição"))
//                }
//            })
//    }



    
    fun fetchMovieDetails(movieId: Int, apiKey: String, language: String) {
        _movieDetails.postValue(MovieResource.Loading(null))
        Single.zip(
            getMoviesAPI().getMovieDetails(movieId, apiKey, language),
            getMoviesAPI().getMovieCast(movieId, apiKey, language),
            BiFunction { movieDetails: MovieDetail, atorResponse: AtorResponse ->
                addElenco(movieDetails, atorResponse)
            })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<MovieDetail>() {
                override fun onSuccess(t: MovieDetail) {
                    _movieDetails.postValue(MovieResource.Success(t))
                }

                override fun onError(e: Throwable) {
                    _movieDetails.postValue(
                        MovieResource.Error(
                            null,
                            "Falha ao realizar a requisição"
                        )
                    )
                }

            })
    }

    private fun addElenco(movieDetails: MovieDetail, atorResponse: AtorResponse): MovieDetail {
        movieDetails.elenco = atorResponse.cast
        return movieDetails
    }


    private fun filtrarGeneroFilme(
        movieResponse: MovieResponse,
        genres: List<Genre>
    ): MovieResponse {
        for (movie in movieResponse.results) {
            movie.setGenres(genres)
        }
        return movieResponse
    }


}