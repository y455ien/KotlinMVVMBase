package com.example.kotlinmvvmbase.core.base.context

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.os.LocaleList
import androidx.annotation.RequiresApi
import com.example.kotlinmvvmbase.core.base.repository.Cache
import java.util.*
import kotlin.collections.LinkedHashSet

object ContextHelper {

    fun attachBaseContext(base: Context?): Context? {
        return updateContext(base)
    }

    private fun updateContext(base: Context?): Context? {
        val locale = Cache.LANGUAGE.getCachedLocale()
        Locale.setDefault(locale)
        val res = base?.resources
        val config = Configuration(res?.configuration)
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            setLocaleForApi24(config, locale)
            base?.createConfigurationContext(config)
        } else {
            config.setLocale(locale)
            base?.createConfigurationContext(config)
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private fun setLocaleForApi24(
            config: Configuration,
            locale: Locale
    ) {
        val set: MutableSet<Locale> = LinkedHashSet()
        // bring the user locale to the front of the list
        set.add(locale)
        val all = LocaleList.getDefault()
        for (i in 0 until all.size()) {
            // append other locales supported by the user
            set.add(all[i])
        }
        val locales = set.toTypedArray()
        config.setLocales(LocaleList(*locales))
    }
}