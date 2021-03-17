package com.example.kotlinmvvmbase.core.base.ui


class SingleLiveEvent<T>(private val value: T) {
    private var isConsumed = false

    fun get(): T? =
        if (isConsumed) {
            null
        } else {
            isConsumed = true
            value
        }
}