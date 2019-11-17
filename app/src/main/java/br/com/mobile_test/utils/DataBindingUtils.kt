package br.com.mobile_test.utils

import android.opengl.Visibility
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.mobile_test.model.Movie
import br.com.mobile_test.model.MovieDetail
import br.com.mobile_test.model.MovieResponse
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.*


//TODO FORMATAR DATA
//TODO FORMATAR ORCAMENTO



@BindingAdapter("loadImage")
fun ImageView.loadImg(url: String?) {
    url?.let {
        Glide.with(this).load("https://image.tmdb.org/t/p/w500$it").into(this)
    }
}


@BindingAdapter("loadImage")
fun ImageView.loadImg(moveDetail: MovieDetail?) {
    moveDetail?.let {
        Glide.with(this).load("https://image.tmdb.org/t/p/w500${it.imageURL}").into(this)
    }
}

@BindingAdapter("setData")
fun TextView.setData(data : String){
    val formater = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    val date = formater.parse(data)
    text = date?.toString()
}

@BindingAdapter("setRating")
fun TextView.setRating(rating :Double?){
    rating?.let {
        text = rating.toString()
    }

}

@BindingAdapter("setRating")
fun TextView.setRating(movieDetail: MovieDetail?){
    visibility = View.INVISIBLE
    movieDetail?.let {
        visibility = View.VISIBLE
        text = it.rating.toString()
    }

}

@BindingAdapter("setGenre")
fun TextView.setGenres(movie: Movie){
    movie.genres?.let {
        var genreString = ""
        for(genre in it) {
            genreString +="${genre.name},"
        }
        text = genreString
    }
}

@BindingAdapter("setVisibility")
fun ProgressBar.setVisibility(boolean: Boolean?){
    boolean?.let {
        visibility = if(it) View.VISIBLE else View.GONE
    }
}

@BindingAdapter("setVisibility")
fun RecyclerView.setVisibility(boolean: Boolean?){
    boolean?.let {
        visibility = if(!it) View.VISIBLE else View.GONE
    }
}

//@BindingAdapter("setVisibility")
//fun TextView.setVisibility(movieResource: MovieResource<MovieDetail>){
//    visibility = if(movieResource is MovieResource.Error){
//        View.VISIBLE
//    }else{
//        View.GONE
//    }
//}

@BindingAdapter("setError")
fun TextView.setError(movieResource: MovieResource<MovieResponse>?){
    movieResource?.let {
        if(it is MovieResource.Error){
            this.visibility = View.VISIBLE
            this.text = it.msg
        }else{
            this.visibility = View.GONE
        }
    }
}

