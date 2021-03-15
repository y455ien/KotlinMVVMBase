package com.example.kotlinmvvmbase.core.base.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.viewbinding.ViewBinding
import com.example.kotlinmvvmbase.R
import com.example.kotlinmvvmbase.databinding.ActivityMainBinding
import com.example.kotlinmvvmbase.util.BaseCommunicator

typealias InflateActivity<T> = (LayoutInflater) -> T

abstract class BaseActivity<VB : ViewBinding>(private val inflate: InflateActivity<VB>) : AppCompatActivity() {
    protected lateinit var binding: VB
    protected var navController: NavController? = null
    protected lateinit var listener: NavController.OnDestinationChangedListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflate.invoke(layoutInflater)
        val view = binding.root
        setContentView(view)
        observeCommunicator()
    }

    private fun observeCommunicator() {
        BaseCommunicator.isLoading.observe(this) { if (it) onShowLoading() else onHideLoading() }
        BaseCommunicator.toast.observe(this) { message -> onShowToast(message) }
        BaseCommunicator.errorToast.observe(this) { error -> onShowErrorToast(error) }
        BaseCommunicator.navigationWithAction.observe(this) { navController?.navigate(it.destination, it.navOptions) }
        BaseCommunicator.authorizationStatus.observe(this) { if (!it) onShowToast("Unauthorized") }
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