package com.intcore.aerbagprovider.util.extension

import android.text.Editable
import android.text.TextWatcher
import com.google.android.material.textfield.TextInputLayout
import com.intcore.aerbagprovider.util.ValidationCriteria
import com.intcore.aerbagprovider.util.Validator

fun TextInputLayout.getValueString(): String {
    return this.editText?.text.toString().trim()
}

fun TextInputLayout.observe(callback: ((changedValue: String?) -> Unit)? = null, vararg validations: ValidationCriteria?) {
    this.editText?.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            s?.let { editable ->
                this@observe.error = validateMulti(s.toString(), *validations)
                callback?.let { callback ->
                    when {
                        editable.isBlank() || this@observe.error != null -> callback(null)
                        else -> callback(editable.toString().trim())
                    }
                }
            }
        }
    })
}

private fun validateMulti(string: String, vararg validations: ValidationCriteria?): String? {
    for (current in validations) {
        val errorString: String? = current?.invoke(string)
        if (errorString != null) return errorString
    }
    return null
}

