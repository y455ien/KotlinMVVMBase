package com.example.kotlinmvvmbase.constant

object Constant {
    object Cache {
        const val PREFERENCE_FILE_NAME = "USER CACHE"
        const val USER_KEY = "USER"
        const val LANGUAGE_KEY = "LANG"
        const val FIRST_TIME_KEY = "FIRST"
        const val ARABIC_LANGUAGE = "ar"
        const val ENGLISH_LANGUAGE = "en"
    }

    object Network {
        const val ACCEPT_HEADER = "application/json"
        const val CONTENT_TYPE_HEADER = "application/json"
        const val READ_TIMEOUT: Long = 60
        const val CONNECT_TIMEOUT: Long = 60
        const val CALL_TIMEOUT: Long = 60
        const val WRITE_TIMEOUT: Long = 60
    }

    object Error {
        const val APP_ERROR = "App will close in 3 seconds"
        const val CLOSE_DURATION: Long = 3000
        const val NETWORK_ERROR_MSG = "Something went wrong"
    }
}
