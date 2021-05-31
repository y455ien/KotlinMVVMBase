package com.example.kotlinmvvmbase.core.base.navigation

import androidx.navigation.NavDirections
import androidx.navigation.NavOptions

class Destination(builder: Builder) {
    val destination: NavDirections = builder.destination
    val navOptions: NavOptions = builder.navOptionsBuilder.build()

    fun getActionId() : Int = destination.actionId

    override fun equals(other: Any?): Boolean = (other is Destination) && this.destination.actionId == other.destination.actionId

    class Builder(var destination: NavDirections) {
        var navOptionsBuilder: NavOptions.Builder = NavOptions.Builder()

        fun setPopUpTo(id: Int, isInclusive: Boolean = false): Builder {
            this.navOptionsBuilder.setPopUpTo(id, isInclusive)
            return this
        }

        fun setLaunchSingleTop(launchSingleTop: Boolean): Builder {
            this.navOptionsBuilder.setLaunchSingleTop(launchSingleTop)
            return this
        }

        fun setEnterAnimation(enterAnimation: Int): Builder {
            this.navOptionsBuilder.setEnterAnim(enterAnimation)
            return this
        }

        fun setExitAnimation(exitAnimation: Int): Builder {
            this.navOptionsBuilder.setExitAnim(exitAnimation)
            return this
        }

        fun setPopEnterAnimation(popEnterAnimation: Int): Builder {
            this.navOptionsBuilder.setPopEnterAnim(popEnterAnimation)
            return this
        }

        fun setPopExitAnimation(popExitAnimation: Int): Builder {
            this.navOptionsBuilder.setPopExitAnim(popExitAnimation)
            return this
        }

        fun build(): Destination {
            return Destination(this)
        }
    }
}