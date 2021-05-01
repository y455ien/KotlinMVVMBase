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
            it.get()?.let { isAuthenticated ->
                if (!isAuthenticated) Toast.makeText(this, "Unauthenticated", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun observeNavigationEvent() {
        vm.getNavigationEvent.observe(this) { it ->
            it.get()?.let {
                navigate(it)
            }
        }
    }

    private fun observeErrorMessageEvent() {
        vm.getErrorEvent.observe(this) {
            it.get()?.let { errorMessage ->
                Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun observeToastEvent() {
        vm.getToastEvent.observe(this) {
            it.get()?.let { toastMessage ->
                Toast.makeText(this, toastMessage, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun observeLoadingEvent() {
        vm.getIsLoadingEvent.observe(this) {
            it.get()?.let { isVisible ->
                if (isVisible) binding.root.findViewById<ProgressBar>(R.id.progressBar).visibility = View.VISIBLE
                else binding.root.findViewById<ProgressBar>(R.id.progressBar).visibility = View.GONE
            }
        }
    }

    abstract fun initViewModel(): VM

    abstract fun navigate(navDestinationWrapper: NavDestinationWrapper?)
}