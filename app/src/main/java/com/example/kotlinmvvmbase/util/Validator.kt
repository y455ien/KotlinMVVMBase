package com.example.kotlinmvvmbase.util

import android.text.TextUtils

typealias ValidationCriteria = (String) -> String?

class Validator {
    companion object {
        const val passwordRegExp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}$"

        fun validateEmpty(string: String): String? {
            return if (TextUtils.isEmpty(string.trim())) "Field can't be empty"
            else null
        }

        fun validateMaxLength(string: String, maximumChar: Int = 25): String? {
            return if (string.trim().length > maximumChar) "Field must be less than $maximumChar characters"
            else null
        }

        fun validateLengthInterval(string: String, minimumChar: Int = 1, maximumChar: Int = 25): String? {
            return if (string.trim().length !in minimumChar until maximumChar) "Field must be $minimumChar to $maximumChar characters"
            else null
        }

        fun validateSpecialCharacters(string: String): String? {
            return if (!string.matches(passwordRegExp.toRegex())) "Field can't contain special characters"
            else null
        }
    }
}