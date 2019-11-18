package br.com.mobile_test.utils


import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.mobile_test.model.Movie
import br.com.mobile_test.model.MovieDetail
import br.com.mobile_test.model.MovieResponse
import br.com.mobile_test.model.genresToString
import com.bumptech.glide.Glide


@BindingAdapter("loadImage")
fun ImageView.loadImg(url: String?) {
    url?.let {
        Glide.with(this).load("https://image.tmdb.org/t/p/w500$it").into(this)
    }
}

@BindingAdapter("moveDetail", "url", requireAll = false)
fun ImageView.loadImg(moveDetail: MovieDetail?, url: String?) {
    moveDetail?.let {
        url?.let {
            Glide.with(this).load("https://image.tmdb.org/t/p/w500${it}").into(this)
        }
    }
}

@BindingAdapter("setValor")
fun TextView.setValor(movieDetail: MovieDetail?) {
    movieDetail?.let {
        text = FormatadorUtils.formataValor(it.orcamento)
    }
}


@BindingAdapter("setDuracao")
fun TextView.setDuracao(movieDetail: MovieDetail?){
    movieDetail?.let {
        text = FormatadorUtils.formataDuracao(it.duracao)
    }
}

@BindingAdapter("setData")
fun TextView.setData(data: String) {
    text = FormatadorUtils.formataData(data)
}

@BindingAdapter("setData")
fun TextView.setData(movieDetail: MovieDetail?) {
    movieDetail?.let {
        text = FormatadorUtils.formataData(it.release_date)
    }
}

@BindingAdapter("setRating")
fun TextView.setRating(rating: Double?) {
    rating?.let {
        text = rating.toString()
    }
}

@BindingAdapter("setRating")
fun TextView.setRating(movieDetail: MovieDetail?) {
    visibility = View.INVISIBLE
    movieDetail?.let {
        visibility = View.VISIBLE
        text = it.rating.toString()
    }

}

@BindingAdapter("setGenre")
fun TextView.setGenres(movie: Movie) {
    movie.genres?.let {
        text = genresToString(it)
    }
}

@BindingAdapter("setGenre")
fun TextView.setGenres(movieDetail: MovieDetail?) {
    movieDetail?.let { details ->
        text = genresToString(details.genres)
    }
}

@BindingAdapter("setVisibility")
fun ProgressBar.setVisibility(boolean: Boolean?) {
    boolean?.let {
        visibility = if (it) View.VISIBLE else View.GONE
    }
}

@BindingAdapter("setVisibility")
fun RecyclerView.setVisibility(boolean: Boolean?) {
    boolean?.let {
        visibility = if (!it) View.VISIBLE else View.GONE
    }
}



@BindingAdapter("setVisibility")
fun TextView.setVisibility(movieResource: MovieResource<MovieDetail>?) {
    movieResource?.let {
        if (it is MovieResource.Success) {
            this.visibility = View.VISIBLE
        } else {
            this.visibility = View.GONE
        }
    }
}

@BindingAdapter("setVisibility")
fun ImageView.setVisibility(movieResource: MovieResource<MovieDetail>?) {
    movieResource?.let {
        if (it is MovieResource.Success) {
            this.visibility = View.VISIBLE
        } else {
            this.visibility = View.GONE
        }
    }
}

