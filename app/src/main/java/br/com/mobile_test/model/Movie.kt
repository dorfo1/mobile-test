package br.com.mobile_test.model

import com.google.gson.annotations.SerializedName

data class Movie(
    val id : Int,
    @SerializedName("poster_path")
    val imageURL : String,
    val title : String,
    @SerializedName("genre_ids")
    val genres_id : List<Int>,
    @SerializedName("vote_average")
    val rating: Double,
    val release_date : String
){
    var genres : List<Genre>? = null
}


fun Movie.setGenres(genres : List<Genre>){
    val list = ArrayList<Genre>()
    for(genre_id in this.genres_id){
        for(genre in genres) {
            if(genre_id == genre.id) list.add(genre)
        }
    }
    this.genres = list
}



data class MovieResponse(
    val page : Int,
    val results : List<Movie>
)


data class MovieDetail(
    val id : Int,
    @SerializedName("poster_path")
    val imageURL : String,
    val title : String,
    @SerializedName("genre_ids")
    val genres: List<Genre>,
    @SerializedName("vote_average")
    val rating: Double,
    val overview : String,
    val release_date : String,
    @SerializedName("budget")
    val orcamento : Double,
    @SerializedName("production_companies")
    val companies : List<Company>,
    @SerializedName("runtime")
    val duracao : Int
){
    var elenco : List<Ator>? = null
}

