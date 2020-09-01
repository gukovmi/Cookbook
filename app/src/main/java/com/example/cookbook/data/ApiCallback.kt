package com.example.cookbook.data

interface ApiCallback<T> {
    fun onSuccess(data: T)
    fun onError(message: String)
}