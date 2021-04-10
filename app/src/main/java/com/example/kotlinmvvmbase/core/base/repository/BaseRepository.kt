package com.example.kotlinmvvmbase.core.base.repository

import com.example.kotlinmvvmbase.core.network_manual_parsing.client.Client
import java.util.*


abstract class BaseRepository() {
    protected val client = Client
//    protected val userCache = Cache.USER
    protected val langCache = Cache.LANGUAGE

    fun validateCache(): Boolean {
        return Cache.isInitialized()
    }

    fun getLocale(): Locale {
        return Cache.LANGUAGE.getCachedLocale()
    }
}