package com.example.kotlinmvvmbase.core.base.repository

import android.content.Context
import android.content.SharedPreferences
import com.example.kotlinmvvmbase.core.config.AppConfig
import com.example.kotlinmvvmbase.constant.Constant
import com.google.gson.Gson
import java.util.*

object Cache {
    private var instance: SharedPreferences? = null
    private val gson = Gson()

    fun initialize(context: Context?) {
        instance = context?.getSharedPreferences(Constant.Cache.PREFERENCE_FILE_NAME, Context.MODE_PRIVATE)
    }

    fun isInitialized(): Boolean = instance != null

//    object USER {
//        fun cacheUser(user: WorkshopUser) {
//            instance!!.run {
//                with(this.edit()) {
//                    putString(Constant.Cache.USER_KEY, gson.toJson(user))
//                    apply()
//                }
//            }
//        }
//
//        fun getCachedUser(): WorkshopUser? {
//            val cachedUser = instance?.getString(Constant.Cache.USER_KEY, null)
//            return cachedUser?.let {
//                gson.fromJson(cachedUser, WorkshopUser::class.java)
//            }
//        }
//
//        fun removeUser() {
//            instance!!.run {
//                with(this.edit()) {
//                    remove(Constant.Cache.USER_KEY)
//                    apply()
//                }
//            }
//        }
//
//        fun isLoggedIn(): Boolean {
//            val cachedUser = instance?.getString(Constant.Cache.USER_KEY, null)
//            return cachedUser != null
//        }
//
//        fun isUserFirstTime(): Boolean {
//            return instance!!.let {
//                val isFirstTime = it.getBoolean(Constant.Cache.FIRST_TIME_KEY, true)
//                if (isFirstTime) {
//                    with(it.edit()) {
//                        putBoolean(Constant.Cache.FIRST_TIME_KEY, false)
//                        apply()
//                    }
//                }
//                return@let isFirstTime
//            }
//        }
//
//    }

    object LANGUAGE {
        fun cacheLanguage(language: String) {
            instance!!.run {
                with(this.edit()) {
                    putString(Constant.Cache.LANGUAGE_KEY, language)
                    apply()
                }
            }
            updateAppConfig()
        }

        fun getCachedLang(): String? {
            return instance?.getString(Constant.Cache.LANGUAGE_KEY, null)
        }

        fun swapLanguageAsync() {
            val currentLang = instance!!.getString(Constant.Cache.LANGUAGE_KEY, Constant.Cache.ENGLISH_LANGUAGE)
            if (currentLang == Constant.Cache.ARABIC_LANGUAGE) cacheLanguage(Constant.Cache.ENGLISH_LANGUAGE)
            else cacheLanguage(Constant.Cache.ARABIC_LANGUAGE)
            updateAppConfig()
        }

        fun getCachedLocale(): Locale {
            val cachedLocaleLanguage = instance?.getString(Constant.Cache.LANGUAGE_KEY, getDefaultLocale().language)
            return Locale(cachedLocaleLanguage ?: getDefaultLocale().language)
        }

        private fun getDefaultLocale(): Locale {
            return when {
                Locale.getDefault().language.contains(Constant.Cache.ENGLISH_LANGUAGE, true) -> Locale(Constant.Cache.ENGLISH_LANGUAGE)
                Locale.getDefault().language.contains(Constant.Cache.ARABIC_LANGUAGE, true) -> Locale(Constant.Cache.ARABIC_LANGUAGE)
                else -> Locale(Constant.Cache.ENGLISH_LANGUAGE)
            }
        }

        fun isLanguageSelected(): Boolean {
            val language: String? =  instance?.getString(Constant.Cache.LANGUAGE_KEY, null)
            return language != null
        }

        private fun updateAppConfig() {
            AppConfig.USER_LANG = getCachedLocale().language
        }
    }
}