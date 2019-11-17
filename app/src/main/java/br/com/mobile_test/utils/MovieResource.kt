package br.com.mobile_test.utils

sealed class MovieResource<T>(
    val data: T? = null,
    val msg: String? = null
) {
    class Success<T>(data: T) : MovieResource<T>(data)
    class Loading<T>(data: T? = null) : MovieResource<T>(data)
    class Error<T>(data: T? = null, msg: String) : MovieResource<T>(data, msg)
}