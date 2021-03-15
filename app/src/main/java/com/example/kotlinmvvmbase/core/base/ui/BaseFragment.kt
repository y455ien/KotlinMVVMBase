package com.example.kotlinmvvmbase.core.base.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavAction
import androidx.navigation.NavDirections
import androidx.viewbinding.ViewBinding
import com.example.kotlinmvvmbase.core.base.navigation.NavDestinationWrapper
import com.example.kotlinmvvmbase.core.base.viewmodel.BaseViewModel
import com.example.kotlinmvvmbase.util.BaseCommunicator

typealias InflateFragment<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment<VB : ViewBinding, VM : BaseViewModel>(private val inflate: InflateFragment<VB>) : Fragment() {
    protected lateinit var vm: VM
    private var _binding: VB? = null
    val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm = initViewModel()
        vm.errorToast.observe(this) { BaseCommunicator.updateErrorToast(it) }
        vm.authorizationStatus.observe(this) { onAuthorizedStatusUpdate(it) }
        vm.loadingStatus.observe(this) { BaseCommunicator.updateLoadingStatus(it) }
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = inflate.invoke(inflater, container, false)
        return binding.root
    }

    protected fun navigateWithAction(destination: NavDestinationWrapper) {
        BaseCommunicator.navigateWithAction(destination)
    }

    protected fun showToast(message: String) {
        BaseCommunicator.updateToast(message)
    }

    private fun onAuthorizedStatusUpdate(authorizationStatus: Boolean) {
        BaseCommunicator.updateAuthorizationStatus(authorizationStatus)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    abstract fun initViewModel(): VM
}