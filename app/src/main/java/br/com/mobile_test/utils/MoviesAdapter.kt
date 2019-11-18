package br.com.mobile_test.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.mobile_test.R
import br.com.mobile_test.databinding.RowMovieItemBinding
import br.com.mobile_test.model.Movie

class MoviesAdapter : PagedListAdapter<Movie, MoviesAdapter.MoviesHolder>(
    movieDiff
) {

    private lateinit var listener: MovieListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<RowMovieItemBinding>(
            inflater,
            R.layout.row_movie_item,
            parent,
            false
        )
        return MoviesHolder(binding)
    }


    override fun onBindViewHolder(holder: MoviesHolder, position: Int) {
        val movie = getItem(position)
        movie.let {
            holder.binding.movie = it
            holder.binding.listener = listener
        }

    }

    fun setOnMovieClickListener(listener: MovieListener) {
        this.listener = listener
    }


    class MoviesHolder(val binding: RowMovieItemBinding) : RecyclerView.ViewHolder(binding.root)

    companion object {
        val movieDiff = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }

        }
    }
}

interface MovieListener {
    fun onMovieClickListener(movie: Movie, view: View)
}

