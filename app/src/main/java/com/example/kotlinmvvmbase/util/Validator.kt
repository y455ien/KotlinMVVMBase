package com.example.kotlinmvvmbase.util

import android.text.TextUtils

class Validator {
    companion object{
        fun isEmpty(string: String): Boolean = TextUtils.isEmpty(string)

        fun validPassword(password: String): Boolean {
            val passwordRegExp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}$"
            if (isEmpty(password)) {
                return false
            } else if (!password.matches(passwordRegExp.toRegex())) {
                return false
            }
            return true
        }
    }
}