package br.com.mobile_test.ui.movies

import android.app.Application
import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import br.com.mobile_test.R
import br.com.mobile_test.model.Movie
import br.com.mobile_test.model.MovieResponse
import br.com.mobile_test.repositories.MoviesRepository
import br.com.mobile_test.ui.movies.paging.MovieDataSourceFactory
import br.com.mobile_test.utils.MovieResource
import br.com.mobile_test.utils.NetworkState
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable

class MoviesViewModel(application: Application) : AndroidViewModel(application) {

    private val context = application


    var movieList: LiveData<PagedList<Movie>>

    private var compositeDisposable = CompositeDisposable()

    private val pageSize = 20

    private val sourceFactory : MovieDataSourceFactory

    init {
        sourceFactory = MovieDataSourceFactory(compositeDisposable,context.resources.getString(R.string.api_key),context.resources.getString(R.string.api_language))
        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize * 2)
            .setPrefetchDistance(5)
            .setEnablePlaceholders(false)
            .build()
        movieList = LivePagedListBuilder<Int,Movie>(sourceFactory,config).build()

    }

    val networkState : LiveData<NetworkState> = Transformations.switchMap(sourceFactory.dataSource){ source ->
        source.networkState
    }

    var loading : LiveData<Boolean> = Transformations.map(networkState) {
        it is NetworkState.Loading
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}