package com.sagarika.common.util

sealed class Resource<T> {
    data class Success<T>(var data: T) : Resource<T>()
    data class Error<T>(var message: String) : Resource<T>()
}