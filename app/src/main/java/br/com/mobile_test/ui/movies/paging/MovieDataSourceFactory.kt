package br.com.mobile_test.ui.movies.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import br.com.mobile_test.model.Movie
import io.reactivex.disposables.CompositeDisposable

class MovieDataSourceFactory (private val compositeDisposable: CompositeDisposable,
                              private val apiKey: String,
                              private val language: String) : DataSource.Factory<Int, Movie>(){

    val dataSource = MutableLiveData<MovieDataSource>()



    override fun create(): DataSource<Int, Movie> {
       val source =  MovieDataSource(compositeDisposable,apiKey,language)
        dataSource.postValue(source)
        return source
    }

}