package com.example.kotlinmvvmbase.core.config

import com.example.kotlinmvvmbase.BuildConfig
import com.example.kotlinmvvmbase.constant.Constant

//ToDo: Add static app configs
class AppConfig {
    companion object{
        val BASE_URL: String = if (BuildConfig.DEBUG) "https://al-mahata.intcore.net" else "http://XX.XX.XX.XXX:XXXX"
        var USER_LANG: String = Constant.Cache.ENGLISH_LANGUAGE
    }
}



