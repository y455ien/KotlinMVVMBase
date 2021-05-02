package com.example.kotlinmvvmbase.core.base.repository

import com.example.kotlinmvvmbase.core.network.client.Client
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import java.util.*


abstract class BaseRepository(private val vmJob: Job, private val dispatcher: CoroutineDispatcher, protected val scope: CoroutineScope = CoroutineScope(vmJob + dispatcher)) {
    protected val client = Client
    protected val cache = Cache

    //    protected val userCache = Cache.USER
    protected val langCache = Cache.LANGUAGE

    fun validateCache(): Boolean {
        return Cache.isInitialized()
    }

    fun getLocale(): Locale {
        return Cache.LANGUAGE.getCachedLocale()
    }
}