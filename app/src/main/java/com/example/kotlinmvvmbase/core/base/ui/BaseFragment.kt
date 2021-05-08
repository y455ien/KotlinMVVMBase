package com.example.kotlinmvvmbase.core.base.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.kotlinmvvmbase.core.base.viewmodel.BaseActivityViewModel
import com.example.kotlinmvvmbase.core.base.viewmodel.BaseFragmentViewModel

typealias InflateFragment<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment<VB : ViewBinding, FVM : BaseFragmentViewModel, AVM : BaseActivityViewModel>(private val inflate: InflateFragment<VB>, private val aVmClass: Class<AVM>) :
        Fragment() {
    protected lateinit var vm: FVM
    private var _binding: VB? = null
    val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm = initViewModel().bind(ViewModelProvider(requireActivity()).get(aVmClass)) as FVM
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = inflate.invoke(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    abstract fun initViewModel(): FVM
}