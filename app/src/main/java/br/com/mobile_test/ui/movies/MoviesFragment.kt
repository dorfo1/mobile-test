package br.com.mobile_test.ui.movies


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager

import br.com.mobile_test.R
import br.com.mobile_test.databinding.FragmentMoviesBinding
import br.com.mobile_test.model.Movie
import br.com.mobile_test.ui.detalhes.DetalhesFragment
import br.com.mobile_test.utils.MovieResource
import kotlinx.android.synthetic.main.fragment_movies.*

class MoviesFragment : Fragment() {

    private lateinit var binding: FragmentMoviesBinding
    private lateinit var moviesViewModel: MoviesViewModel
    private lateinit var adapter : MoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movies, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        moviesViewModel = ViewModelProviders.of(this).get(MoviesViewModel::class.java)

        binding.lifecycleOwner = this
        binding.viewmodel = moviesViewModel

        //moviesViewModel.fetchMovies()

        rvFilmes.layoutManager = LinearLayoutManager(context)
        adapter = MoviesAdapter()
        rvFilmes.adapter = adapter

        setObservers()

        adapter.setOnMovieClickListener(object :MovieListener{
            override fun onMovieClickListener(movie: Movie,view: View) {
                Navigation.findNavController(view).navigate(MoviesFragmentDirections.actionMoviesFragmentToDetalhesFragment3(movie.id))
            }

        })
    }

    private fun setObservers() {
        moviesViewModel.movieList.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }

}
