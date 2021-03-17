package com.example.kotlinmvvmbase.core.base.repository

import android.content.Context
import android.content.SharedPreferences
import com.example.kotlinmvvmbase.core.config.AppConfig
import com.example.kotlinmvvmbase.constant.Constant
import java.util.*

object Cache {
    private var instance: SharedPreferences? = null

    fun isInitialized(): Boolean = instance != null

    fun initialize(context: Context?) {
        instance = context?.getSharedPreferences(Constant.Cache.PREFERENCE_FILE_NAME, Context.MODE_PRIVATE)
        instance?.let {
            if (it.getBoolean(Constant.Cache.FIRST_TIME_KEY, true)) {
                with(it.edit()) {
                    putBoolean(Constant.Cache.FIRST_TIME_KEY, false)
                    putString(Constant.Cache.LANGUAGE_KEY, getDefaultLocale().language)
                    apply()
                }
            }
            updateUserLang()
        }
    }

    fun swapLanguageAsync() {
        val currentLang = instance!!.getString(Constant.Cache.LANGUAGE_KEY, Constant.Cache.ENGLISH_LANGUAGE)
        if (currentLang == Constant.Cache.ARABIC_LANGUAGE) cacheLanguage(Constant.Cache.ENGLISH_LANGUAGE)
        else cacheLanguage(Constant.Cache.ARABIC_LANGUAGE)
        updateUserLang()
    }

    fun getCachedUser() {

    }

    fun cacheUser() {
        instance!!.run {
            with(this.edit()) {
                //ToDo: Cache user
                apply()
            }
        }
    }

    fun removeUser() {
        instance!!.run {
            with(this.edit()) {
                remove(Constant.Cache.USER_KEY)
                apply()
            }
        }
    }

    fun getCachedLocale(): Locale {
        val cachedLocaleLanguage = instance?.getString(Constant.Cache.LANGUAGE_KEY, getDefaultLocale().language)
        return Locale(cachedLocaleLanguage ?: getDefaultLocale().language)
    }

    private fun cacheLanguage(language: String) {
        instance!!.run {
            with(this.edit()) {
                putString(Constant.Cache.LANGUAGE_KEY, language)
                apply()
            }
        }
        updateUserLang()
    }

    private fun getDefaultLocale(): Locale {
        return when {
            Locale.getDefault().language.contains(Constant.Cache.ENGLISH_LANGUAGE, true) -> Locale(Constant.Cache.ENGLISH_LANGUAGE)
            Locale.getDefault().language.contains(Constant.Cache.ARABIC_LANGUAGE, true) -> Locale(Constant.Cache.ARABIC_LANGUAGE)
            else -> Locale(Constant.Cache.ENGLISH_LANGUAGE)
        }
    }

    private fun updateUserLang() {
        AppConfig.USER_LANG = getCachedLocale().language
    }

}