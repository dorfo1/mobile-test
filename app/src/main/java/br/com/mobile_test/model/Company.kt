package br.com.mobile_test.model

import com.google.gson.annotations.SerializedName

data class Company(
    val id: Int,
    @SerializedName("logo_path")
    val logo: String,
    val name : String
)