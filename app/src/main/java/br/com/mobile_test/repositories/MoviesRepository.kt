package br.com.mobile_test.repositories


import androidx.lifecycle.MutableLiveData
import br.com.mobile_test.api.getMoviesAPI
import br.com.mobile_test.model.*
import br.com.mobile_test.utils.MovieResource
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

object MoviesRepository {


    private val _movieDetails = MutableLiveData<MovieResource<MovieDetail>>()
    val movieDetails get() = _movieDetails

    fun fetchMovieDetails(movieId: Int, apiKey: String, language: String) {
        _movieDetails.postValue(MovieResource.Loading(null))
        Single.zip(
            getMoviesAPI().getMovieDetails(movieId, apiKey, language),
            getMoviesAPI().getMovieCast(movieId, apiKey, language),
            BiFunction { movieDetails: MovieDetail, atorResponse: AtorResponse ->
                movieDetails.elenco = atorResponse.cast
                movieDetails
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



}