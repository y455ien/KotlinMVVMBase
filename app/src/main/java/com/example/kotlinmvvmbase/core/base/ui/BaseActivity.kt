package com.example.kotlinmvvmbase.core.base.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.viewbinding.ViewBinding
import com.example.kotlinmvvmbase.core.base.repository.Cache
import com.example.kotlinmvvmbase.core.base.context.ContextHelper
import com.example.kotlinmvvmbase.databinding.ActivityMainBinding
import com.example.kotlinmvvmbase.constant.Constant
import kotlinx.coroutines.*
import kotlin.system.exitProcess

typealias InflateActivity<T> = (LayoutInflater) -> T

abstract class BaseActivity<VB : ViewBinding>(private val inflate: InflateActivity<VB>) :
    AppCompatActivity() {
    protected lateinit var binding: VB
    protected var navController: NavController? = null
    protected lateinit var listener: NavController.OnDestinationChangedListener

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(ContextHelper.attachBaseContext(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflate.invoke(layoutInflater)
        val view = binding.root
        setContentView(view)
        checkCacheInitialization()
        observeCommunicator()
    }

    private fun checkCacheInitialization() {
        if (!Cache.isInitialized()) {
            onShowErrorToast(Constant.Error.APP_ERROR)
            lifecycleScope.launch {
                delay(Constant.Error.CLOSE_DURATION)
                this@BaseActivity.finish()
                exitProcess(0)
            }
        }
    }

    private fun observeCommunicator() {
        observeLoadingEvent()
        observeToastEvent()
        observeErrorMessageEvent()
        observeNavigationEvent()
        observeUnauthenticatedEvent()
        observeSwapLanguageEvent()
    }

    private fun observeSwapLanguageEvent() {
        BaseCommunicator.getSwapLanguageEvent.observe(this) {
            it.get()?.let {
                recreate()
            }
        }
    }

    private fun observeUnauthenticatedEvent() {
        BaseCommunicator.getUnauthenticatedEvent.observe(this) {
            it.get()?.let {
                if (!it) onShowErrorToast("Unauthenticated")
            }
        }
    }

    private fun observeNavigationEvent() {
        BaseCommunicator.getNavigationEvent.observe(this) { it ->
            // Added action availability on current destination checkup first
            // On recreating activity on language swap, Activity gets recreated which in turn listen to the latest Action emitted by the live data.
            // Thats why make sure to keep all your state in the View Model ya Besho =) , it survives life cycle and configuration changes
            it.get()?.let {
                if (navController?.currentDestination?.getAction(it.destination.actionId) != null) {
                    navController?.navigate(it.destination, it.navOptions)
                }
            }
        }
    }

    private fun observeErrorMessageEvent() {
        BaseCommunicator.getErrorEvent.observe(this) {
            it.get()?.let {
                onShowErrorToast(it)
            }
        }
    }

    private fun observeToastEvent() {
        BaseCommunicator.getToastEvent.observe(this) {
            it.get()?.let {
                onShowToast(it)
            }
        }
    }

    private fun observeLoadingEvent() {
        BaseCommunicator.getIsLoadingEvent.observe(this) {
            it.get()?.let {
                if (it) onShowLoading() else onHideLoading()
            }
        }
    }

    private fun onShowLoading() {
        (binding as? ActivityMainBinding)?.progressBar?.visibility = View.VISIBLE
    }

    private fun onHideLoading() {
        (binding as? ActivityMainBinding)?.progressBar?.visibility = View.GONE
    }

    private fun onShowToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun onShowErrorToast(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController?.navigateUp() ?: super.onSupportNavigateUp()
    }

    override fun onResume() {
        super.onResume()
        navController?.addOnDestinationChangedListener(listener)
    }

    override fun onPause() {
        navController?.removeOnDestinationChangedListener(listener)
        super.onPause()
    }
}