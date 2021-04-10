package com.example.kotlinmvvmbase.core.base.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.viewbinding.ViewBinding
import com.example.kotlinmvvmbase.R
import com.example.kotlinmvvmbase.core.base.viewmodel.BaseActivityViewModel
import com.example.kotlinmvvmbase.core.base.context.ContextHelper
import com.example.kotlinmvvmbase.core.base.navigation.NavDestinationWrapper

typealias InflateActivity<T> = (LayoutInflater) -> T

abstract class BaseActivity<VB : ViewBinding, VM : BaseActivityViewModel>(private val inflate: InflateActivity<VB>) :
    AppCompatActivity() {
    protected lateinit var vm: VM
    protected lateinit var binding: VB
//    protected var navController: NavController? = null
//    protected lateinit var listener: NavController.OnDestinationChangedListener

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(ContextHelper.attachBaseContext(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflate.invoke(layoutInflater)
        val view = binding.root
        setContentView(view)
        vm = initViewModel()
        observeViewModel()
    }

    private fun observeViewModel() {
        observeLoadingEvent()
        observeToastEvent()
        observeErrorMessageEvent()
        observeNavigationEvent()
        observeUnauthenticatedEvent()
        observeSwapLanguageEvent()
    }

    private fun observeSwapLanguageEvent() {
        vm.getSwapLanguageEvent.observe(this) {
            it.get()?.let {
                recreate()
            }
        }
    }

    private fun observeUnauthenticatedEvent() {
        vm.getUnauthenticatedEvent.observe(this) {
            it.get()?.let {
                if (!it) onShowErrorToast("Unauthenticated")
            }
        }
    }

    private fun observeNavigationEvent() {
        vm.getNavigationEvent.observe(this) { it ->
            it.get()?.let {
//                if (navController?.currentDestination?.getAction(it.destination.actionId) != null) {
//                    navController?.navigate(it.des1tination, it.navOptions)
//                }
                navigate(it)
            }
        }
    }

    private fun observeErrorMessageEvent() {
        vm.getErrorEvent.observe(this) {
            it.get()?.let {
                onShowErrorToast(it)
            }
        }
    }

    private fun observeToastEvent() {
        vm.getToastEvent.observe(this) {
            it.get()?.let {
                onShowToast(it)
            }
        }
    }

    private fun observeLoadingEvent() {
        vm.getIsLoadingEvent.observe(this) {
            it.get()?.let {
                if (it) onShowLoading() else onHideLoading()
            }
        }
    }

//    override fun onSupportNavigateUp(): Boolean {
////        return navController?.navigateUp() ?: super.onSupportNavigateUp()
//    }

    override fun onResume() {
        super.onResume()
//        navController?.addOnDestinationChangedListener(listener)
    }

    override fun onPause() {
//        navController?.removeOnDestinationChangedListener(listener)
        super.onPause()
    }

    abstract fun initViewModel(): VM

    private fun onShowLoading() {
        binding.root.findViewById<ProgressBar>(R.id.progressBar).visibility = View.VISIBLE
    }

    private fun onHideLoading() {
        binding.root.findViewById<ProgressBar>(R.id.progressBar).visibility = View.GONE
    }

    private fun onShowToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun onShowErrorToast(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

    abstract fun navigate(navDestinationWrapper: NavDestinationWrapper?)
}