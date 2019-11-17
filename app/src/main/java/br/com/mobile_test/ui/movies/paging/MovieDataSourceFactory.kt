package br.com.mobile_test.ui.movies.paging

import androidx.paging.DataSource
import br.com.mobile_test.model.Movie
import io.reactivex.disposables.CompositeDisposable

class MovieDataSourceFactory (private val compositeDisposable: CompositeDisposable,
                              private val apiKey: String,
                              private val language: String) : DataSource.Factory<Int, Movie>(){



    override fun create(): DataSource<Int, Movie> {
       return MovieDataSource(compositeDisposable,apiKey,language)
    }

}