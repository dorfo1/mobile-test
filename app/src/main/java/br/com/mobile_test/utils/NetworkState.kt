package br.com.mobile_test.utils

sealed class NetworkState (
    val msg:String? = null
){
    class Success : NetworkState()
    class Loading : NetworkState()
    class Error(msg:String) : NetworkState(msg)
}