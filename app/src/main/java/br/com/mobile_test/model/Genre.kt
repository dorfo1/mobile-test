package br.com.mobile_test.model

data class Genre(
    val id : Int,
    val name : String
)

data class GenreResponse(
    val genres : List<Genre>
)