package br.com.mobile_test.ui.buscar


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
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager

import br.com.mobile_test.R
import br.com.mobile_test.databinding.FragmentBuscarBinding
import br.com.mobile_test.model.Movie
import br.com.mobile_test.utils.MovieListener
import br.com.mobile_test.utils.MoviesAdapter
import br.com.mobile_test.utils.NetworkState
import kotlinx.android.synthetic.main.fragment_buscar.*


class SearchFragment : Fragment() {

    private lateinit var binding: FragmentBuscarBinding
    private lateinit var adapter: MoviesAdapter
    private lateinit var searchViewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_buscar, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchViewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)
        binding.viewmodel = searchViewModel

        rvFilmes.layoutManager = LinearLayoutManager(context)
        adapter = MoviesAdapter()
        rvFilmes.adapter = adapter

        adapter.setOnMovieClickListener(object : MovieListener {
            override fun onMovieClickListener(movie: Movie, view: View) {
                Navigation.findNavController(view)
                    .navigate(SearchFragmentDirections.actionBuscarFragmentToDetalhesFragment(movie.id))
            }

        })
        setUpObservers()
    }

    private fun setUpObservers() {
        searchViewModel.searchEtContent.observe(viewLifecycleOwner, Observer { query ->
            query?.let {
                searchViewModel.movieQuery(it)
            }
        })

        searchViewModel.searchedMovies.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })


        searchViewModel.networkState.observe(viewLifecycleOwner, Observer {
            if (it is NetworkState.Error) Toast.makeText(
                context,
                "Falha ao buscar filmes",
                Toast.LENGTH_SHORT
            ).show()
        })
    }


}
