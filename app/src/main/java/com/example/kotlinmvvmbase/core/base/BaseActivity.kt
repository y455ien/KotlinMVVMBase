package com.example.kotlinmvvmbase.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.viewbinding.ViewBinding
import com.example.kotlinmvvmbase.R
import com.example.kotlinmvvmbase.databinding.ActivityMainBinding

typealias InflateActivity<T> = (LayoutInflater) -> T

abstract class BaseActivity<VB: ViewBinding> (private val inflate: InflateActivity<VB>) : AppCompatActivity() {
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
        BaseCommunicator.isLoading.observe(
                this,
                { isLoading ->
                    if (isLoading) onShowLoading() else onHideLoading()
                })
        BaseCommunicator.toast.observe(
                this,
                { message -> Toast.makeText(this, message, Toast.LENGTH_LONG).show() })
        BaseCommunicator.navigation.observe(
                this,
                { navigationId -> navController?.navigate(navigationId) })
    }

    private fun onShowLoading() {
        (binding as? ActivityMainBinding)?.progressBar?.visibility = View.VISIBLE
    }

    private fun onHideLoading() {
        (binding as? ActivityMainBinding)?.progressBar?.visibility = View.VISIBLE
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