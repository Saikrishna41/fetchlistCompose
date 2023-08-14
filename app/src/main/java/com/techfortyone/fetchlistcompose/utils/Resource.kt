package com.techfortyone.fetchlistcompose.utils

class Resource<out T> constructor(
    val status: Status,
    val data: T?,
    val message: String?
) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(status = Status.SUCCESS, data = data, message = null)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(status = Status.LOADING, data = data, message = null)
        }

        fun <T> error(data: T?, message: String?): Resource<T> {
            return Resource(status = Status.ERROR, data = data, message = message)
        }

    }
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}