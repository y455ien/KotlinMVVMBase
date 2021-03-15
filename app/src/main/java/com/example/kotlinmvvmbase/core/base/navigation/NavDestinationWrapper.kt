package com.example.kotlinmvvmbase.core.base.navigation

import androidx.navigation.NavDirections
import androidx.navigation.NavOptions

class NavDestinationWrapper(val destination: NavDirections,
                            private val popUpTo: Int? = null,
                            private val isInclusive: Boolean = false,
                            val navOptions: NavOptions = NavOptions.Builder().run {
                                popUpTo?.let {
                                    setPopUpTo(popUpTo, isInclusive)
                                }
                                build()
                            })