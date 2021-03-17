package com.example.kotlinmvvmbase.core.config

import com.example.kotlinmvvmbase.BuildConfig
import com.example.kotlinmvvmbase.constant.Constant

//ToDo: Add static app configs
class AppConfig {
    companion object{
        val BASE_URL: String = if (BuildConfig.DEBUG) "http://18.193.126.108:8080" else "http://XX.XX.XX.XXX:XXXX"
        var USER_LANG: String = Constant.Cache.ENGLISH_LANGUAGE
    }
}



