package br.com.mobile_test.ui.detalhes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import br.com.mobile_test.R
import br.com.mobile_test.model.Movie
import br.com.mobile_test.model.MovieDetail
import br.com.mobile_test.model.MovieResponse
import br.com.mobile_test.repositories.MoviesRepository
import br.com.mobile_test.utils.MovieResource

class DetalhesViewModel(application: Application) : AndroidViewModel(application){


    private val context = application

    val movieDetailResource: LiveData<MovieResource<MovieDetail>> = MoviesRepository.movieDetails

    val loading: LiveData<Boolean> = Transformations.map(movieDetailResource) { movieDetailResource ->
        movieDetailResource is MovieResource.Loading
    }

    val movieDetails: LiveData<MovieDetail> = Transformations.map(movieDetailResource) { resource ->
        resource is MovieResource.Success
        resource.data
    }


    fun fetchMovieDetails(id : Int) = MoviesRepository.fetchMovieDetails(
        id,
        context.resources.getString(R.string.api_key),
        context.resources.getString(R.string.api_language)
    )
}