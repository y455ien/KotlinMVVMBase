package com.example.kotlinmvvmbase.core.base.repository

import com.example.kotlinmvvmbase.core.base.ui.BaseCommunicator
import com.example.kotlinmvvmbase.core.network_manual_parsing.client.Client


abstract class BaseRepository() {
    protected val client = Client
//    protected val userCache = Cache.USER
    protected val langCache = Cache.LANGUAGE

    fun validateCache(): Boolean {
        return Cache.isInitialized()
    }
}