package br.com.mobile_test.ui.movies.paging

import android.util.Log
import androidx.paging.ItemKeyedDataSource
import androidx.paging.PageKeyedDataSource
import br.com.mobile_test.api.GenreAPI
import br.com.mobile_test.api.MovieAPI
import br.com.mobile_test.api.getGenreAPI
import br.com.mobile_test.api.getMoviesAPI
import br.com.mobile_test.model.*
import br.com.mobile_test.repositories.MoviesRepository
import br.com.mobile_test.utils.MovieResource
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class MovieDataSource(
    private val compositeDisposable: CompositeDisposable,
    private val apiKey: String,
    private val language: String
) : PageKeyedDataSource<Int, Movie>() {


    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Movie>) {
        val disposable = createSingle(1)
        requestObservable(disposable,1,2,callback,null)

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        val disposable = createSingle(params.key)
        requestObservable(disposable,params.key,params.key+1,null,callback)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {

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

    private fun requestObservable(disposable: Single<MovieResponse>,page:Int,nextPage:Int,initialCallback:LoadInitialCallback<Int, Movie>?,callback: LoadCallback<Int, Movie>?){
        compositeDisposable.add(
            disposable.subscribe({response ->
                initialCallback?.onResult(response.results,null,nextPage)
                callback?.onResult(response.results,nextPage)
            },{throwable ->
                Log.d("TAG","Error next page")
            })
        )
    }


    private fun createSingle(page: Int): Single<MovieResponse> {
        return Single.zip(
            getMoviesAPI().getMovies(apiKey, language, page),
            getGenreAPI().getGenres(apiKey, language),
            BiFunction { movieResponse: MovieResponse, genreResponse: GenreResponse ->
                filtrarGeneroFilme(movieResponse, genreResponse.genres)
            })
    }

}