package br.com.mobile_test.model

data class Ator(
    val id: Int,
    val character: String,
    val name: String
)


data class AtorResponse(
    val id: Int,
    val cast: List<Ator>
)