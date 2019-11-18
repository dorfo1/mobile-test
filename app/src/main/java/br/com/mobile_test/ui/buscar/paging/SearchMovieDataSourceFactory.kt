package br.com.mobile_test.ui.buscar.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import br.com.mobile_test.model.Movie
import io.reactivex.disposables.CompositeDisposable

class SearchMovieDataSourceFactory(
    private val query : String,
    private val compositeDisposable: CompositeDisposable,
    private val apiKey: String,
    private val language: String
) : DataSource.Factory<Int, Movie>() {

    val dataSource = MutableLiveData<SearchMovieDataSource>()

    override fun create(): DataSource<Int, Movie> {
        val source = SearchMovieDataSource(query,compositeDisposable, apiKey, language)
        dataSource.postValue(source)
        return source
    }

}