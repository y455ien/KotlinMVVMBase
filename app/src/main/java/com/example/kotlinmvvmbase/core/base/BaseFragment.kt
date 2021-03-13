package com.example.kotlinmvvmbase.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

typealias InflateFragment<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment<VB: ViewBinding> (private val inflate: InflateFragment<VB>) : Fragment() {
    private var _binding: VB? = null
    val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initMembers()
        observeLiveData()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate.invoke(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getInitialData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    protected fun navigate(destinationId: Int) {
        BaseCommunicator.navigate(destinationId)
    }

    protected fun showLoading() {
        BaseCommunicator.updateLoadingStatus(true)
    }

    protected fun hideLoading() {
        BaseCommunicator.updateLoadingStatus(false)
    }

    protected fun showToast(message: String) {
        BaseCommunicator.updateToastStatue(message)
    }

    abstract fun initMembers()

    abstract fun observeLiveData()

    abstract fun getInitialData()
}