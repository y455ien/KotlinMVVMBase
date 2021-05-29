package com.example.kotlinmvvmbase.util.adapter.hybrid.holder

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class HybridViewHolder<T, VB: ViewBinding>(viewBinding: ViewBinding) : RecyclerView.ViewHolder(viewBinding.root) {
    @Suppress("UNCHECKED_CAST")
    protected var binding = viewBinding as VB

    abstract fun bind(item: T)
}