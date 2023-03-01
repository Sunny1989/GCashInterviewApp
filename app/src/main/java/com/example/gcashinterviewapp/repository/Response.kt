package com.example.gcashinterviewapp.repository

sealed class Response<T>(val data1 : T? = null, val errorMsg1 : String? = null){
    class Success<T>(data: T?) : Response<T>(data1 = data)
    class Loading<T> : Response<T>()
    class Error<T>(errorMsg:  String) : Response<T>(errorMsg1 = errorMsg)
}
