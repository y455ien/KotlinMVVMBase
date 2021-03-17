package com.example.kotlinmvvmbase.core.base.repository

import com.example.kotlinmvvmbase.core.base.ui.BaseCommunicator
import com.example.kotlinmvvmbase.core.network_manual_parsing.client.Client


abstract class BaseRepository() {
    protected val client = Client
    private val cache = Cache

    fun cacheUser() {
        //ToDO: Implement method once User model provided
    }

    fun getCachedUser() {
        //ToDO: Implement method once User model provided
    }

    fun getCachedLanguage() {

    }

    fun removeCachedUser() {
        cache.removeUser()
    }

    fun swapLanguage() {
        cache.swapLanguageAsync()
        BaseCommunicator.pushSwapLanguageEvent(true)
    }

}