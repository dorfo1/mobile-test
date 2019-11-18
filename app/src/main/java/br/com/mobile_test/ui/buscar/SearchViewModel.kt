package br.com.mobile_test.ui.buscar

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import br.com.mobile_test.R
import br.com.mobile_test.model.Movie
import br.com.mobile_test.ui.buscar.paging.SearchMovieDataSource
import br.com.mobile_test.ui.buscar.paging.SearchMovieDataSourceFactory
import br.com.mobile_test.utils.NetworkState
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.*

class SearchViewModel (application: Application) : AndroidViewModel(application){

    private val REQUEST_TIME = 500L

    private val context = application


    lateinit var  movieList: LiveData<PagedList<Movie>>


    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    private var job : Job? = null


    private var compositeDisposable = CompositeDisposable()


    val searchEtContent = MutableLiveData<String>()
    val searchedMovies = MediatorLiveData<PagedList<Movie>>()
    val dataSource = MediatorLiveData<SearchMovieDataSource>()

    private val pageSize = 20

    private lateinit var sourceFactory : SearchMovieDataSourceFactory



    fun movieQuery(query: String) {
        if(query.length>3){
            job?.cancel()
            job = coroutineScope.launch {
                delay(REQUEST_TIME)
                initSourceFactory(query)
            }
        }
    }

    private fun initSourceFactory(query: String){
        sourceFactory = SearchMovieDataSourceFactory(query,compositeDisposable,context.resources.getString(R.string.api_key),context.resources.getString(R.string.api_language))
        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize * 2)
            .setPrefetchDistance(5)
            .setEnablePlaceholders(false)
            .build()
        movieList = LivePagedListBuilder<Int,Movie>(sourceFactory,config).build()
        searchedMovies.addSource(movieList){
            searchedMovies.postValue(it)
        }

        dataSource.addSource(sourceFactory.dataSource){
            dataSource.postValue(it)
        }
    }

    val networkState : LiveData<NetworkState> = Transformations.switchMap(dataSource){
        it.networkState
    }



    var loading : LiveData<Boolean> = Transformations.map(networkState) {
        it is NetworkState.Loading
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
        job?.cancel()

    }

}